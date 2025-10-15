package com.kawaiicanvas.kawaicanvas.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// felhantering kommer inom kort
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createNewCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElse(null);
    }

}
