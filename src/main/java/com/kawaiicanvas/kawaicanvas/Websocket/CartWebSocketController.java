package com.kawaiicanvas.kawaicanvas.Websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CartWebSocketController {

    @Autowired
    private CartUpdateService cartUpdateService;

    // lägger till en tavla i kundvagnen
    @MessageMapping("/cart/add")
    @SendTo("/topic/cart")
    public CartUpdateMessage addToCart(CartUpdateMessage message) {

        System.out.println("Adding to cart: " + message.getCartId());
        return cartUpdateService.addToCart(message.getCartId());

    }

}
