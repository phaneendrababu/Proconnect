package com.example.SpringMongoDB.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.SpringMongoDB.model.User;

public interface UserRepositoryCrud extends CrudRepository<User, String>{

}
