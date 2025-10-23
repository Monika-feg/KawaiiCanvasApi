package com.kawaiicanvas.kawaicanvas.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.Cart.model.CartItem;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    // test för hämta order med hjälp av id och om den hittar rätt order
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

        CartItem item1 = new CartItem();
        item1.setId("item1");
        item1.setCanvas(canvas1);
        item1.setNumberOfCanvases(1);

        CartItem item2 = new CartItem();
        item2.setId("item2");
        item2.setCanvas(canvas2);
        item2.setNumberOfCanvases(2);

        // skapa en ny kundvagn
        Cart cart = new Cart();
        cart.setId("testCartId");
        cart.setItems(new ArrayList<>()); // Initialisera listan först
        cart.getItems().add(item1);
        cart.getItems().add(item2);

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
        assertEquals(2, result.getCart().getItems().size());
    }

    // om den inte hittar order med angivet id
    @Test
    public void getOrderByIdNotFoundTest() {

        // skapa nya tavlor
        Canvas canvas1 = new Canvas();
        canvas1.setId("1");
        canvas1.setTitle("Cute Cat");
        canvas1.setPrice("80");
        Canvas canvas2 = new Canvas();
        canvas2.setId("2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("50");

        CartItem item1 = new CartItem();
        item1.setId("item1");
        item1.setCanvas(canvas1);
        item1.setNumberOfCanvases(1);

        CartItem item2 = new CartItem();
        item2.setId("item2");
        item2.setCanvas(canvas2);
        item2.setNumberOfCanvases(2);

        // skapa en ny kundvagn
        Cart cart = new Cart();
        cart.setId("testCartId");
        cart.setItems(new ArrayList<>()); // Initialisera listan först
        cart.getItems().add(item1);
        cart.getItems().add(item2);

        // skapa en ny order
        Order order = new Order();
        order.setId("");
        order.setCart(cart);

        when(orderRepository.findById("testId")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrderById("testId");
        });

        assertEquals("Could not find order with id: testId", exception.getMessage());
    }

}
