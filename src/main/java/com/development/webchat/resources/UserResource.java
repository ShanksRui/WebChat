package com.development.webchat.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.development.webchat.model.entities.User;
import com.development.webchat.services.UserService;
@RestController
@RequestMapping("/users")
public class UserResource {

	private final UserService service;
	
	public UserResource(UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(users);
	}
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable String id){
		User c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
