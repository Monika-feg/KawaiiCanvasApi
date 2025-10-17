package com.kawaiicanvas.kawaicanvas.Websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class CartUpdateService {

    private final Map<String, CartUpdateMessage> carts = new ConcurrentHashMap<>();

    // lägger till en tavla i kundvagnen
    public CartUpdateMessage addToCart(String cartId) {
        if (cartId == null) {
            throw new IllegalAccessError(" cartId cannot be null");
        }
        // hämtar kundvagnen eller skapar en ny om den inte finns
        CartUpdateMessage cart = carts.computeIfAbsent(cartId, id -> new CartUpdateMessage(id, 0));
        // lägger till en tavla
        cart.setCanvasCount(cart.getCanvasCount() + 1);
        return cart;

    }

}
