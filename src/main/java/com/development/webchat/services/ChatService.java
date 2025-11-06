package com.development.webchat.services;

import org.springframework.stereotype.Service;

import com.development.webchat.repositories.ChatRepository;
@Service
public class ChatService {

	
	private final ChatRepository repository;
	
	public ChatService(ChatRepository repository) {
		this.repository = repository;
	}
	

	
}
