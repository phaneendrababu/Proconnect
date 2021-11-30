package com.example.SpringMongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.SpringMongoDB.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String>{

}
