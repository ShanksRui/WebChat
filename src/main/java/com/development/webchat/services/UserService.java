package com.development.webchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.UserDTO;
import com.development.webchat.model.entities.DTO.UserSaveDTO;
import com.development.webchat.model.entities.DTO.UserUpdateDTO;
import com.development.webchat.repositories.UserRepository;
import com.development.webchat.services.exceptions.NotFoundObjectException;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> op = repository.findById(id);
		return op.orElseThrow(() -> new NotFoundObjectException("Id:" + id + " not found"));
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public User update(User user) {
		User entity = findById(user.getId());
		updateEntityFields(entity, user);
		repository.save(entity);
		return entity;
	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getStatus());
	}

	public User fromSaveDTO(UserSaveDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setStatus(dto.getStatus());
		return user;
	}

	public User fromUpdateDTO(UserUpdateDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setStatus(dto.getStatus());
		return user;
	}

	private void updateEntityFields(User entity, User user) {
		entity.setName(user.getName());
		entity.setPassword(user.getPassword());
		entity.setStatus(user.getStatus());
	}
}
