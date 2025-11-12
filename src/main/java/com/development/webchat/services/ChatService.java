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
		if(repository.existsById(id0)&&repository.existsById(id1))	{
			throw new IllegalArgumentException("this chat exists between those two users!");
		}
		Chat chat = new Chat();
		chat.setUser0Id(id0);	
		chat.setUser1Id(id1);
		chat.getMessages().add(message);	
		return repository.insert(chat);
		
	}
}

