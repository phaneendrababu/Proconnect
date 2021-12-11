package com.example.ProConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProConnect.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String>{

}
