package com.development.webchat.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.development.webchat.model.entities.User;

public interface UserRepository extends MongoRepository<User, String>{

	List<User> findByNameContainingIgnoreCase(String name);
	
	@Query(value = "{ 'status' : ?0 }", fields = "{ 'name' : 1, 'status' : 1 }")
	List<User> searchStatus(String status);
	
}
