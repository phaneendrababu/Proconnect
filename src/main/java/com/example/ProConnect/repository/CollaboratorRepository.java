package com.example.ProConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProConnect.model.Collaborator;

public interface CollaboratorRepository extends MongoRepository<Collaborator, String>{

}
