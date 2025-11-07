package com.development.webchat.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.repositories.ChatRepository;
@Service
public class ChatService {

	
	private final ChatRepository repository;

	public ChatService(ChatRepository repository) {
		this.repository = repository;
	}
	
	public List<Chat> findAll(){
		return repository.findAll();
	}
}

