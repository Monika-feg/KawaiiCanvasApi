package com.kawaiicanvas.kawaicanvas.Payment;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.Cart.CartService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> createPayment(String cartId) {
        BigDecimal amount = cartService.getTotalPrice(cartId);

        if (cartId == null || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid request. cartId and amount are required"));
        }

        String checkoutUrl = paymentService.implemetCheckout(amount, cartId);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("url", checkoutUrl);

        return ResponseEntity.ok(responseBody);
    }

}
