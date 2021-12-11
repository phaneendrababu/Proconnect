package com.example.ProConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProConnect.model.User;


public interface UserRepository extends MongoRepository<User, String>{

}
