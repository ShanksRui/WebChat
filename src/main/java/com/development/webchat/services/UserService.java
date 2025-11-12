package com.development.webchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.UserDTO;
import com.development.webchat.repositories.UserRepository;
import com.development.webchat.services.exceptions.NotFoundObjectException;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> op = repository.findById(id);
		return op.orElseThrow(() -> new NotFoundObjectException(id + "not found"));
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User insert(User user) {
		User u = user;
		return repository.insert(u);		
	}
	
	public void update (User user) {
		User entity = findById(user.getId());
		updateEntity(entity, user);
	    repository.save(entity);
	}
	
	public User updateFromDTO (UserDTO dto) {
	   return new User(dto.getId(), dto.getName(), dto.getStatus());
	}
	public void updateEntity(User entity,User user) {
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		entity.setStatus(user.getStatus());
	}
}
