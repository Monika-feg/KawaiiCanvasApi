package com.kawaiicanvas.kawaicanvas.Order;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kawaiicanvas.kawaicanvas.Cart.CartRepository;
import com.kawaiicanvas.kawaicanvas.Cart.CartService;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    // Spara nyorder
    public Order saveNewOrder(Order order, String cartId) {

        try {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new RuntimeException("Could not find cart with id: " + cartId));
            order.setCart(cart);
            BigDecimal totalprice = cartService.getTotalPrice(cartId);
            order.setTotalPrice(totalprice);
            order.setItems(new ArrayList<>(cart.getItems()));
            return orderRepository.save(order);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Order data is invalid ", e);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not create order ", e);
        }

    }

    // hämta order med hjälp av id
    public Order getOrderById(String id) {
        try {
            System.out.println("Söker order med id: " + id);
            return orderRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Could not find order with id: " + id));
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException(" Order data is invalid ", e);
        }

    }

}
