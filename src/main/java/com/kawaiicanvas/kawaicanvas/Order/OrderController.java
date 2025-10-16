package com.kawaiicanvas.kawaicanvas.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // lägg till ny order
    @PostMapping("/{cartId}")
    public Order createOrder(@RequestBody Order order, @PathVariable String cartId) {
        return orderService.saveNewOrder(order, cartId);
    }

    // hämta order med hjälp av id
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

}
