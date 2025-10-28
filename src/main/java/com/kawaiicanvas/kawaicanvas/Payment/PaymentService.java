package com.kawaiicanvas.kawaicanvas.Payment;

import org.springframework.stereotype.Service;

import com.kawaiicanvas.kawaicanvas.Cart.CartService;
import com.kawaiicanvas.kawaicanvas.Order.Order;
import com.kawaiicanvas.kawaicanvas.Order.OrderRepository;
import com.kawaiicanvas.kawaicanvas.Websocket.InventoryService;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Value("${stripe.success.url2}")
    private String stripeSuccessUrl;

    @Value("${stripe.cancel.url}")
    private String stripeCancelUrl;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private InventoryService inventoryService;

    // Initiera Stripe med API-nyckel
    public void initStripe() {
        Stripe.apiKey = stripeApiKey;
    }

    public String implementCheckout(String orderId) {

        try {
            initStripe();

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("Id not found: " + orderId));

            BigDecimal amount = order.getTotalPrice();

            SessionCreateParams params = SessionCreateParams.builder()
                    // skickas till hit om betalningen lyckas
                    .setSuccessUrl(stripeSuccessUrl + "?session_id={CHECKOUT_SESSION_ID}")

                    // skickas hit om betalningen avbtyts
                    .setCancelUrl(stripeCancelUrl)
                    // definierar en produkt
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    // vilken valuta
                                                    .setCurrency("sek")
                                                    // stripe jobbar med ören så 1000 öre är 100 kr
                                                    .setUnitAmount(amount.multiply(BigDecimal.valueOf(100)).longValue())
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    // denna ger produkten ett namn jag vill synas i
                                                                    // stripe dashboard
                                                                    .setName("Canvas # " + order.getCart().getId())
                                                                    .build())
                                                    .build()

                                    )

                                    .setQuantity(1L)
                                    .build())

                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .build();

            Session session = Session.create(params);

            Payment payment = new Payment();
            payment.setOrderId(order.getId());
            payment.setStripePaymentId(session.getId());
            payment.setPaymentStatus("paying");
            payment.setAmount(amount);
            payment.setUrl(session.getUrl());

            paymentRepository.save(payment);

            order.setPayment(payment);
            orderRepository.save(order);

            return session.getUrl();

        } catch (Exception e) {
            System.err.println(" Stripe error: " + e.getMessage());
            return "Fel vid betalning, försök igen senare";

        }
    }

    public boolean isPaymentSuccessful(String sessionId) {
        try {
            // hämtar session ifrån stripe
            System.out.println("sessionId: " + sessionId);
            Session session = Session.retrieve(sessionId);
            System.out.println("Stripe session status: " + session.getPaymentStatus());
            // kollar om betalningen är lyckad
            if ("paid".equals(session.getPaymentStatus()) || "complete".equals(session.getPaymentStatus())) {
                Payment payment = paymentRepository.findByStripePaymentId(sessionId);
                if (payment != null) {
                    String orderId = payment.getOrderId();
                    // Hämta order-objektet för att kunna tömma rätt cart
                    Order order = orderRepository.findById(orderId).orElse(null);
                    // Uppdatera lagerstatus INNAN kundvagnen töms
                    inventoryService.handleInventoryUpdate(orderId);
                    System.out.println("✅ Payment successful for session: " + sessionId);
                    // töm kundvagnen efter lageruppdatering
                    if (order != null && order.getCart() != null) {
                        cartService.clearCart(order.getCart().getId());
                    }
                }
                return true;
            } else {
                System.out.println("ℹ️ Payment not successful for session: " + sessionId + " Status: "
                        + session.getPaymentStatus());
                return false;
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error checking payment: " + e.getMessage());
            return false;

        }

    }

    // Hämta Payment via sessionId
    public Payment getPaymentBySessionId(String sessionId) {
        return paymentRepository.findByStripePaymentId(sessionId);
    }

    // Hämta Order via orderId
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

}
