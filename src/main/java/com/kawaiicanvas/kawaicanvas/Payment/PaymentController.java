package com.kawaiicanvas.kawaicanvas.Payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;
import com.kawaiicanvas.kawaicanvas.Order.Order;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = { "http://localhost:5173/", "https://kawaiicanvas.netlify.app" }, allowCredentials = "true")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // skapar en betalning och returnerar checkout URL
    @PostMapping()
    public ResponseEntity<KawaiiResponse<Map<String, String>>> createPayment(@RequestParam String orderId) {
        try {
            if (orderId == null) {
                return ResponseEntity.badRequest()
                        .body(KawaiiResponse.error("Invalid request. orderId is required"));
            }
            String url = paymentService.implementCheckout(orderId);
            Map<String, String> response = new HashMap<>();
            response.put("url", url);
            return ResponseEntity.ok(KawaiiResponse.success("Checkout URL created successfully", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }
    }

    // hanterar lyckad betalning
    @GetMapping("/success")
    public ResponseEntity<KawaiiResponse<Object>> paymentSuccess(@RequestParam String sessionId) {
        try {
            boolean isSuccess = paymentService.isPaymentSuccessful(sessionId);
            if (isSuccess) {
                // Hämta payment och order
                Payment payment = paymentService.getPaymentBySessionId(sessionId);
                if (payment == null) {
                    return ResponseEntity.badRequest().body(KawaiiResponse.error("Payment not found"));
                }
                String orderId = payment.getOrderId();
                Order order = paymentService.getOrderById(orderId);
                if (order == null) {
                    return ResponseEntity.badRequest().body(KawaiiResponse.error("Order not found"));
                }
                return ResponseEntity.ok(KawaiiResponse.success("Payment successful", order));
            } else {
                return ResponseEntity.badRequest().body(KawaiiResponse.error("Payment not successful"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }
    }

}
