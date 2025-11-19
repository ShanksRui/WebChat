package com.development.webchat.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.UserDTO;
import com.development.webchat.model.entities.DTO.UserSaveDTO;
import com.development.webchat.model.entities.DTO.UserUpdateDTO;
import com.development.webchat.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	private final UserService service;

	public UserResource(UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> dtos = users.stream().map(UserDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserSaveDTO dto) {
		User user = service.fromSaveDTO(dto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDTO(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserUpdateDTO dto) {
		User user = service.fromUpdateDTO(dto);
		user.setId(id);
		service.update(user);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
}
