package com.development.webchat.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.development.webchat.model.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

	List<User> findByNameContainingIgnoreCase(String name);
}
