package com.kawaiicanvas.kawaicanvas.Order;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findCartById(String cartId);

}
