package com.development.webchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.Status;
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

	public User update(String id, UserUpdateDTO dto) {
		User entity = findById(id);
		updateEntityFields(entity, dto);
		return repository.save(entity);
	}

	public List<User> searchName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}

	// OFFLINE ->1
	// ONLINE ->2
	// DO_NOT_DISTURB ->3
	// ABSENT ->4
	public List<User> searchStatus(int status) {
		Status st = Status.valueOf(status);
		return repository.searchStatus(st.name());

	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getStatus());
	}

	public User fromSaveDTO(UserSaveDTO dto) {
		return new User(dto.getName(), dto.getPassword(), dto.getStatus());
	}

	private void updateEntityFields(User entity, UserUpdateDTO dto) {
		if (dto.getName() != null && !dto.getName().isBlank())
			entity.setName(dto.getName());

		if (dto.getPassword() != null & !dto.getPassword().isBlank())
			entity.setPassword(dto.getPassword());

		if (dto.getStatus() != null)
			entity.setStatus(dto.getStatus());
	}
}
