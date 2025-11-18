package com.development.webchat.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.Message;
import com.development.webchat.repositories.ChatRepository;
import com.development.webchat.repositories.UserRepository;
import com.development.webchat.services.exceptions.NotFoundObjectException;
@Service
public class ChatService {

	private final ChatRepository repository;
    private final UserRepository userRepository;
	
	public ChatService(ChatRepository repository,UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
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
		
		User user0 = userRepository.findById(chat.getUser0Id()).orElseThrow();
		User user1 = userRepository.findById(chat.getUser1Id()).orElseThrow();
		
		String name = message.getAuthorMsg().getName();
		
		boolean isValidAuthor = 
				name.equalsIgnoreCase(user0.getName()) || name.equalsIgnoreCase(user1.getName());
		        	
		if(isValidAuthor) {
			chat.getMessages().add(message);
			chat.setFirstChat(message.getMommentMsg());
			chat.setLastActivity(message.getMommentMsg());
			return repository.save(chat);			
		}else {
			throw new IllegalArgumentException("name of author is not contains in chat");
		}
		
		
	}
}

