package com.kawaiicanvas.kawaicanvas.Websocket;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Canvas.CanvasRepository;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.Cart.model.CartItem;
import com.kawaiicanvas.kawaicanvas.Order.Order;
import com.kawaiicanvas.kawaicanvas.Order.OrderRepository;

@Component
public class InventoryService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CanvasRepository canvasRepository;

    private final SimpMessagingTemplate messageTemplate;

    public InventoryService(SimpMessagingTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public void handleInventoryUpdate(String orderId) {
        // hämta order från orderRepository med hjälp av itemId
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find order with id: " + orderId));
        // hämta kundvagn
        Cart cart = order.getCart();
        // för en loop genom kundvagnsartiklar och uppdatera lagersaldo
        for (CartItem item : cart.getItems()) {
            // för varje canvas i ordern, uppdatera lagersaldo
            Canvas canvas = canvasRepository.findById(item.getCanvas().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Could not find canvas with id: " + item.getCanvas().getId()));
            canvas.setStockQuantity(canvas.getStockQuantity() - item.getNumberOfCanvases());
            canvasRepository.save(canvas);

            // skicka live uppdatering till frontend
            messageTemplate.convertAndSend("/topic/stock",
                    new Inventory(canvas.getId(), canvas.getTitle(), canvas.getStockQuantity()));
        }

    }

}
