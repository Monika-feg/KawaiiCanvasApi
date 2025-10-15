package com.kawaiicanvas.kawaicanvas.Cart;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping()
    public Cart createNewCart(@RequestBody Cart cart) {
        return cartService.createNewCart(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable String id) {
        return cartService.getCartById(id);

    }

}
