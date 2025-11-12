package com.development.webchat.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.services.ChatService;

@RestController
@RequestMapping("/chats")
public class ChatResource {

	private final ChatService service;
	
	public ChatResource(ChatService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Chat>> findAll(){
		List<Chat> chats = service.findAll();
		return ResponseEntity.ok().body(chats);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Chat> findById(@PathVariable String id){
		Chat c = service.findById(id);
		return ResponseEntity.ok().body(c);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
