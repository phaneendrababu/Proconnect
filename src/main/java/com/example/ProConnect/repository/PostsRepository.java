package com.example.ProConnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProConnect.model.Posts;

public interface PostsRepository extends MongoRepository<Posts, String>{


}
