package com.kawaiicanvas.kawaicanvas.Order;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;
import com.kawaiicanvas.kawaicanvas.Customer.Customer;
import com.kawaiicanvas.kawaicanvas.Payment.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private String id;
    private BigDecimal totalPrice;
    private Customer customer;
    @DocumentReference
    private Cart cart;
    @DocumentReference
    private Payment payment;

}
