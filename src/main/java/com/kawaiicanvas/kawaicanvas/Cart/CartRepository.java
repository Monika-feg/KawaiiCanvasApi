package com.kawaiicanvas.kawaicanvas.Cart;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

}
