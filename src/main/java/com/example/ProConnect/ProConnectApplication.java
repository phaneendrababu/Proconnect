package com.example.ProConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ProConnectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProConnectApplication.class, args);
	}

}