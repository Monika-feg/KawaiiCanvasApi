package com.kawaiicanvas.kawaicanvas.User;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUserName(String userName);

}
