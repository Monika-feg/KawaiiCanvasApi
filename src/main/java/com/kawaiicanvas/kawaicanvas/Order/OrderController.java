package com.kawaiicanvas.kawaicanvas.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.kawaiicanvas.kawaicanvas.KawaiiResponse.KawaiiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// för felhantering har jag inspirerats av dessa
// https://www.baeldung.com/spring-rest-openapi-documentation
// https://www.baeldung.com/swagger-operation-vs-apiresponse
@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = { "http://localhost:5173/", "https://kawaiicanvas.netlify.app" }, allowCredentials = "true")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
})
public class OrderController {

    @Autowired
    private OrderService orderService;

    // lägg till ny order
    @PostMapping("/{cartId}")
    public ResponseEntity<KawaiiResponse<Order>> createOrder(@RequestBody Order order, @PathVariable String cartId,
            HttpServletResponse response) {
        try {
            Order createdOrder = orderService.saveNewOrder(order, cartId);
            Cookie orderCookie = new Cookie("orderId", createdOrder.getId());
            orderCookie.setHttpOnly(true);
            orderCookie.setSecure(true);
            orderCookie.setPath("/");
            orderCookie.setMaxAge(60 * 60); // Sätter cookien att vara giltig i 60 minuter
            response.addCookie(orderCookie);
            return ResponseEntity.ok(KawaiiResponse.success("Order created successfully", createdOrder));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }

    }

    // hämta order med hjälp av id
    @GetMapping("/{id}")
    public ResponseEntity<KawaiiResponse<Order>> getOrderById(@PathVariable String id) {
        try {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok(KawaiiResponse.success("Order retrieved successfully", order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(KawaiiResponse.error(e.getMessage()));
        }
    }

}
