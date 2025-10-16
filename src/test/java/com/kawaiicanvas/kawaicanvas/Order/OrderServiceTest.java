package com.kawaiicanvas.kawaicanvas.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Cart.Cart;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    // test för hämta order med hjälp av id
    @Test
    public void getOrderByIdTest() {

        // skapa nya tavlor
        Canvas canvas1 = new Canvas();
        canvas1.setId("1");
        canvas1.setTitle("Cute Cat");
        canvas1.setPrice("80");
        Canvas canvas2 = new Canvas();
        canvas2.setId("2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("50");

        // skapa en ny kundvagn
        Cart cart = new Cart();
        cart.setId("testCartId");
        cart.setCanvases(new ArrayList<>()); // Initialisera listan först
        cart.getCanvases().add(canvas1);
        cart.getCanvases().add(canvas2);

        // skapa en ny order
        Order order = new Order();
        order.setId("testOrderId");
        order.setCart(cart);

        // mocka repository beteende - när findById anropas med "testOrderId", returnera
        // order
        when(orderRepository.findById("testOrderId")).thenReturn(Optional.of(order));

        // anropa metoden som ska testas
        Order result = orderService.getOrderById("testOrderId");

        // verifiera resultatet
        assertNotNull(result);
        assertEquals("testOrderId", result.getId());
        assertEquals(cart, result.getCart());
        assertEquals("testCartId", result.getCart().getId());
        assertEquals(2, result.getCart().getCanvases().size());
    }

}
