package com.kawaiicanvas.kawaicanvas.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kawaiicanvas.kawaicanvas.Cart.Cart;
import com.kawaiicanvas.kawaicanvas.Cart.CartRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    // Spara nyorder
    public Order saveNewOrder(Order order, String cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            order.setCart(cart);
            return orderRepository.save(order);
        }
        return order;
    }

    // hämta order med hjälp av id
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

}
