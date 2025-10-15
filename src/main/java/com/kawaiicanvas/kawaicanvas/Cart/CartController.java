package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/newCart")
    public Cart createNewCart(@RequestBody Cart cart) {
        return cartService.createNewCart(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable String id) {
        return cartService.getCartById(id);

    }

    @PostMapping("/{cartId}/canvas/{canvasId}")
    public Cart addCanvasToCart(@PathVariable String cartId, @PathVariable String canvasId) {
        return cartService.addCanvasToCart(cartId, canvasId);
    }

    @DeleteMapping("/{cartId}/canvas/{canvasId}")
    public Cart removeCanvasFromCart(@PathVariable String cartId, @PathVariable String canvasId) {
        return cartService.removeCanvasFromCart(cartId, canvasId);
    }

    @GetMapping("/{cartId}/totalPrice")
    public BigDecimal getTotalPrice(@PathVariable String cartId) {
        return cartService.getTotalPrice(cartId);
    }

}
