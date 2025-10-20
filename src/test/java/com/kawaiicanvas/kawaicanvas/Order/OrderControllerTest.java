package com.kawaiicanvas.kawaicanvas.Order;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.kawaiicanvas.kawaicanvas.Canvas.Canvas;
import com.kawaiicanvas.kawaicanvas.Cart.Cart;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    @WithMockUser(username = "testAdmin", roles = "ADMIN")
    public void getOrderByIdTest() throws Exception {
        // skapa ny canvas
        Canvas canvas = new Canvas();
        canvas.setId("1");
        canvas.setTitle("Cute Cat");
        canvas.setPrice("80");
        Canvas canvas2 = new Canvas();
        canvas2.setId("2");
        canvas2.setTitle("Adorable Dog");
        canvas2.setPrice("50");

        // skpa ny cart
        Cart cart = new Cart();
        cart.setId("testCartId");
        cart.setCanvases(new ArrayList<>());
        cart.getCanvases().add(canvas);
        cart.getCanvases().add(canvas2);
        // skapa ny order
        Order order = new Order();
        order.setId("testOrderId");
        order.setCart(cart);

        // mocka repository beteende
        when(orderService.getOrderById("testOrderId")).thenReturn(order);

        // anropa metoden som ska testas
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/{id}", "testOrderId")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Order retrieved successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("testOrderId"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.cart.id").value("testCartId"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.cart.canvases").isArray());

    }

}
