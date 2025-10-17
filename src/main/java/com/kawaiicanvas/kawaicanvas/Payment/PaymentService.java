package com.kawaiicanvas.kawaicanvas.Payment;

import org.springframework.stereotype.Service;

import com.kawaiicanvas.kawaicanvas.Cart.CartService;
import com.kawaiicanvas.kawaicanvas.Order.Order;
import com.kawaiicanvas.kawaicanvas.Order.OrderRepository;
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

    @Value("${stripe.success.url}")
    private String stripeSuccessUrl;

    @Value("${stripe.cancel.url}")
    private String stripeCancelUrl;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartService cartService;

    // Initiera Stripe med API-nyckel
    public void initStripe() {
        Stripe.apiKey = stripeApiKey;
    }

    public String implemetCheckout(BigDecimal amount, String cartId) {
        try {
            initStripe();

            Order order = orderRepository.findCartById(cartId)
                    .orElseThrow(() -> new IllegalArgumentException("Id not found: " + cartId));

            SessionCreateParams params = SessionCreateParams.builder()
                    // skickas till hit om betalningen lyckas
                    .setSuccessUrl(stripeSuccessUrl)
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
                                                                    // denna ger produkten ett namn jag
                                                                    .setName("Canvas # " + cartId)
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
            payment.setPaymentStatus("pending");
            payment.setAmount(amount);

            paymentRepository.save(payment);

            order.setPayment(payment);
            orderRepository.save(order);

            // töm kundvagnen efter betalning
            cartService.clearCart(cartId);

            return session.getUrl();

        } catch (Exception e) {
            System.err.println(" Stripe error: " + e.getMessage());
            return "Fel vid betalning, försök igen senare";

        }
    }
}
