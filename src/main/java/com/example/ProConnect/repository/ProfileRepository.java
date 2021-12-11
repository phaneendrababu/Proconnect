package com.example.ProConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProConnect.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String>{

}
