package com.kawaiicanvas.kawaicanvas.Payment;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.Cart.CartService;
import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/payments")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<KawaiiResponse<String>> createPayment(@RequestParam String cartId) {
        try {
            BigDecimal amount = cartService.getTotalPrice(cartId);

            if (cartId == null || amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest()
                        .body(KawaiiResponse.error("Invalid request. cartId and amount are required"));
            }

            String checkoutUrl = paymentService.implemetCheckout(amount, cartId);
            return ResponseEntity.ok(KawaiiResponse.success("Checkout URL created successfully", checkoutUrl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }

    }

}
