package com.example.ProConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProConnect.model.Connection;



public interface ConnectionRepository extends MongoRepository<Connection, String>{

}
