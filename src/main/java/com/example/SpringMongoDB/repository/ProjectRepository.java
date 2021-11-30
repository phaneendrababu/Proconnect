package com.example.SpringMongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.SpringMongoDB.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

}
