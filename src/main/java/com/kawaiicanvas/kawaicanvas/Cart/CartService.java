package com.kawaiicanvas.kawaicanvas.Cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Canvas.CanvasRepository;

// felhantering kommer inom kort
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CanvasRepository canvasRepository;

    public Cart createNewCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart addCanvasToCart(String cartId, String canvasId) {
        Cart cart = getCartById(cartId);
        Canvas canvas = canvasRepository.findById(canvasId).orElse(null);

        boolean isCanvasInCart = cart.getCanvases().stream()
                .anyMatch(existingCanvas -> existingCanvas.getId().equals(canvasId));

        if (!isCanvasInCart) {
            cart.getCanvases().add(canvas);
            return cartRepository.save(cart);
        }

        return cart;
    }

}
