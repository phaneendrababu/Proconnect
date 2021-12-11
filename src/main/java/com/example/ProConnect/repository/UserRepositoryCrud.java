package com.example.ProConnect.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.ProConnect.model.User;

public interface UserRepositoryCrud extends CrudRepository<User, String>{

}
