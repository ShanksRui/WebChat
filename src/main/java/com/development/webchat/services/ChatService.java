package com.development.webchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.repositories.ChatRepository;
import com.development.webchat.services.exceptions.NotFoundObjectException;
@Service
public class ChatService {

	
	private final ChatRepository repository;

	public ChatService(ChatRepository repository) {
		this.repository = repository;
	}
	
	public List<Chat> findAll(){
		return repository.findAll();
	}
	
	public Chat findById(String id) {
		Optional<Chat> op = repository.findById(id);
		return op.orElseThrow(() -> new NotFoundObjectException(id + "not found"));
	}
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
}

