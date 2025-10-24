package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// för felhantering har jag inspirerats av dessa
// https://www.baeldung.com/spring-rest-openapi-documentation
// https://www.baeldung.com/swagger-operation-vs-apiresponse
// för cookie https://www.baeldung.com/java-servlet-cookies-session och https://www.geeksforgeeks.org/springboot/working-with-cookies-in-spring-mvc-using-cookievalue-annotation/
@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = { "http://localhost:5173", "https://kawaiicanvas.netlify.app" }, allowCredentials = "true")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class CartController {

    @Autowired
    private CartService cartService;

    // skapa ny kundvagn
    @PostMapping("/newCart")
    public ResponseEntity<KawaiiResponse<Cart>> createNewCart(
            @RequestBody Cart cart,
            HttpServletResponse response,
            // https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/cookievalue.html
            @CookieValue(value = "cartId", required = false) String cartId) {
        try {
            // kollar om en cookie med cartId redan finns
            if (cartId != null) {
                Cart existingCart = cartService.getCartById(cartId);
                if (existingCart != null) {
                    return ResponseEntity.ok(KawaiiResponse.success("Cart already exists", existingCart));
                }
            }
            Cart newCart = cartService.createNewCart(cart);
            Cookie cartCookie = new Cookie("cartId", newCart.getId());
            cartCookie.setPath("/");
            // Sätter cookien att vara giltig i 5 dagar
            cartCookie.setMaxAge(60 * 60 * 24 * 5);
            response.addCookie(cartCookie);
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
    @PatchMapping("/{cartId}/canvas/{canvasId}")
    public ResponseEntity<KawaiiResponse<Cart>> addCanvasToCart(@PathVariable String cartId,
            @PathVariable String canvasId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpServletResponse response) {
        try {
            Cart updatedCart = cartService.addCanvasToCart(cartId, canvasId, quantity);
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
