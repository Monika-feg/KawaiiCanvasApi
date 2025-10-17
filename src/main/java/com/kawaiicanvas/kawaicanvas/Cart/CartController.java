package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    // skapa ny kundvagn
    @PostMapping("/newCart")
    public ResponseEntity<KawaiiResponse<Cart>> createNewCart(@RequestBody Cart cart) {
        try {
            Cart newCart = cartService.createNewCart(cart);
            return ResponseEntity.ok(KawaiiResponse.success("Created cart successfully", newCart));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));

        }

    }

    // hämta kundvagn med id
    @GetMapping("/{id}")
    public ResponseEntity<KawaiiResponse<Cart>> getCartById(@PathVariable String id) {
        try {
            Cart foundCart = cartService.getCartById(id);
            return ResponseEntity.ok(KawaiiResponse.success("Found cart successfully", foundCart));
        } catch (IllegalAccessError e) {
            return ResponseEntity.notFound().build();

        }

    }

    // lägger till canvas till kunvagnen
    @PostMapping("/{cartId}/canvas/{canvasId}")
    public ResponseEntity<KawaiiResponse<Cart>> addCanvasToCart(@PathVariable String cartId,
            @PathVariable String canvasId) {
        try {
            Cart updatedCart = cartService.addCanvasToCart(cartId, canvasId);
            return ResponseEntity.ok(KawaiiResponse.success("Added canvas to cart successfully", updatedCart));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));

        }

    }

    // tar bort tavla från kunvagnen
    @DeleteMapping("/{cartId}/canvas/{canvasId}")
    public ResponseEntity<KawaiiResponse<Cart>> removeCanvasFromCart(@PathVariable String cartId,
            @PathVariable String canvasId) {
        try {
            Cart updatedCart = cartService.removeCanvasFromCart(cartId, canvasId);
            return ResponseEntity.ok(KawaiiResponse.success("Removed canvas from cart successfully", updatedCart));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));

        }

    }

    // hämtar totalpriset
    @GetMapping("/{cartId}/totalPrice")
    public ResponseEntity<KawaiiResponse<BigDecimal>> getTotalPrice(@PathVariable String cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(KawaiiResponse.success("Fetched total price successfully", totalPrice));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));

        }

    }

}
