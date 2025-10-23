package com.kawaiicanvas.kawaicanvas.Cart;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.Config.SecurityConfig;

@WebMvcTest(CartController.class)
// denna fick jag hjälp med från Chatgpt ( hade aldrig kommait på ett den
// saknades)
@Import(SecurityConfig.class)
public class CartControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private CartService cartService;

        @Autowired
        private ObjectMapper objectMapper;

        // skapa en ny kundvagn med en tom lista av tavlor
        @Test
        @WithMockUser(username = "Testadmin", roles = "ADMIN")
        public void createNewCart() throws Exception {

                // skapa en ny kundvagn med en tom lista av tavlor
                Cart inputCart = new Cart();
                inputCart.setItems(Arrays.asList());
                // mockat svar från servicen
                Cart returnedCart = new Cart();
                returnedCart.setId("testCartId");
                returnedCart.setItems(Arrays.asList());

                // mockar service-svar
                Mockito.when(cartService.createNewCart(Mockito.any(Cart.class))).thenReturn(returnedCart);
                // utför POST-förfrågan och verifierar svaret
                mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/newCart")
                                .contentType(MediaType.APPLICATION_JSON)
                                // konverterar inputCart till JSON och skickar med i förfrågan
                                .content(objectMapper.writeValueAsString(inputCart)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("testCartId"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.data.items").isArray());

        }

        // om det inte går att skapa en ny kundvagn
        @Test
        @WithMockUser(username = "Testadmin", roles = "ADMIN")
        public void createNewCartBadRequest() throws Exception {

                // skapa en ny kundvagn med en tom lista av tavlor
                Cart inputCart = new Cart();
                inputCart.setItems(Arrays.asList());

                Mockito.when(cartService.createNewCart(Mockito.any(Cart.class)))
                                .thenThrow(new IllegalArgumentException("Could not create cart"));

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/cart/newCart")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                // konverterar inputCart till JSON och skickar med i förfrågan
                                                .content(objectMapper.writeValueAsString(inputCart)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

}
