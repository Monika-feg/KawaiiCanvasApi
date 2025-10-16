package com.kawaiicanvas.kawaicanvas.Cart;

import java.math.BigDecimal;

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

    // skapa en ny kundvagn
    public Cart createNewCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // hitta kundvagn med hjälp av id
    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElse(null);
    }

    // Lägger till en tavla till kundvagnen
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

    // tar bort vara ifrån kundvagnen
    public Cart removeCanvasFromCart(String cartId, String canvasId) {
        Cart cart = getCartById(cartId);
        Canvas canvas = canvasRepository.findById(canvasId).orElse(null);

        if (cart != null && canvas != null) {
            cart.getCanvases().removeIf(existingCanvas -> existingCanvas.getId().equals(canvasId));
            return cartRepository.save(cart);
        }
        return cart;
    }

    // räkna ut totalpriset i kundvagnen
    public BigDecimal getTotalPrice(String cartId) {
        Cart cart = getCartById(cartId);
        BigDecimal totalPrice = BigDecimal.ZERO;
        // leverans avgift
        BigDecimal deliveryFee = new BigDecimal("49");
        // här är gränsen för fri frakt
        BigDecimal freeDeliveryThreshold = new BigDecimal("200");
        // lägger ihop priset för varje tavla i kundvagnen
        for (var canvas : cart.getCanvases()) {
            totalPrice = totalPrice.add(new BigDecimal(canvas.getPrice()));
        }

        // om totalpriset är under 200 kr läggs leveransavgiften på totalpriset
        if (totalPrice.compareTo(freeDeliveryThreshold) < 0) {
            totalPrice = totalPrice.add(deliveryFee);
        }
        return totalPrice;

    }

    // töm varukorgen
    public Cart clearCart(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.getCanvases().forEach(canvas -> canvas.setCart(null));
            cart.getCanvases().clear();
            return cartRepository.save(cart);
        }
        return cart;

    }

}
