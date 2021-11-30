package com.example.SpringMongoDB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.SpringMongoDB.model.Posts;

public interface PostsRepository extends MongoRepository<Posts, String>{


}
