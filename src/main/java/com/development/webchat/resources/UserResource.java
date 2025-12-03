package com.development.webchat.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.UserDTO;
import com.development.webchat.model.entities.DTO.UserSaveDTO;
import com.development.webchat.model.entities.DTO.UserUpdateDTO;
import com.development.webchat.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {

	private final UserService service;

	public UserResource(UserService service) {
		this.service = service;
	}

	@Operation(summary = "List of all Users")
	@ApiResponse(responseCode = "200", description = "List returned with successful")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = service.findAll();
		List<UserDTO> dtos = users.stream().map(UserDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}

	@Operation(summary = "Returns a user by id")
	@ApiResponse(responseCode = "200", description = "User returned with successful")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@Operation(summary = "Delete user by id ")
	@ApiResponse(responseCode = "204", description = "Deleted user with successful")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Create a user")
	@ApiResponse(responseCode = "201", description = "Createded user with successful")
	@PostMapping
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserSaveDTO dto) {
		User user = service.fromSaveDTO(dto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDTO(user));
	}

	@Operation(summary = "Update a user")
	@ApiResponse(responseCode = "200", description = "Updated user with successful")
	@PatchMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id, @Valid @RequestBody UserUpdateDTO dto) {
		User user = service.update(id, dto);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	@GetMapping("/searchname")
	public ResponseEntity<List<User>> findByName(@RequestParam String name) {
		List<User> list = service.searchName(name);
		return ResponseEntity.ok().body(list);
	}
}
