package com.kawaiicanvas.kawaicanvas.Cart;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kawaiicanvas.kawaicanvas.Cart.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

}
