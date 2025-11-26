package com.development.webchat.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.DTO.Message;
import com.development.webchat.services.ChatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/chats")
public class ChatResource {

	private final ChatService service;

	public ChatResource(ChatService service) {
		this.service = service;
	}

	
	@Operation(summary = "List of all chats")
	@ApiResponse(responseCode = "200",description = "List returned with successful")
	@GetMapping
	public ResponseEntity<List<Chat>> findAll() {
		List<Chat> chats = service.findAll();
		return ResponseEntity.ok().body(chats);
	}
	
	@Operation(summary = "returns a chat")
	@ApiResponse(responseCode = "200",description = "Chat returned with successful")
	@GetMapping("/{id}")
	public ResponseEntity<Chat> findById(@PathVariable String id) {
		Chat c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
	
	@Operation(summary = "Delete chat by id")
	@ApiResponse(responseCode = "200",description = "chat deleted with successful")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "create chat")
	@ApiResponse(responseCode = "200",description = "created chat with successful")
	@PostMapping("/{id0}/{id1}")
	public ResponseEntity<Chat> insert(@PathVariable String id0, @PathVariable String id1,
			@RequestBody Message message) {
		Chat chat = service.insert(id0, id1, message);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(chat.getId()).toUri();
		return ResponseEntity.created(uri).body(chat);
	}

}
