package com.development.webchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.DTO.Message;
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
	
	public Chat insert(String id0,String id1,Message message) {
		Chat chat = repository.findChatBetween(id0, id1)
				.orElseGet(() -> {
		            Chat newChat = new Chat();
		            newChat.setUser0Id(id0);	
		            newChat.setUser1Id(id1);
		            return repository.insert(newChat);
				});	
		chat.getMessages().add(message);
		return repository.save(chat);
	}
}

