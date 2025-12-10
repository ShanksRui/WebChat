package com.development.webchat.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.Message;
import com.development.webchat.repositories.ChatRepository;
import com.development.webchat.repositories.UserRepository;
import com.development.webchat.services.exceptions.ChatBetweenUsersNotFoundException;
import com.development.webchat.services.exceptions.MessageAuthorNotInChatException;
import com.development.webchat.services.exceptions.NotFoundObjectException;

@Service
public class ChatService {

	private final ChatRepository repository;
	private final UserRepository userRepository;

	public ChatService(ChatRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	public List<Chat> findAll() {
		return repository.findAll();
	}

	public Chat findById(String id) {
		Optional<Chat> op = repository.findById(id);
		return op.orElseThrow(() -> new NotFoundObjectException("Id:" + id + " not found"));
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public Chat insert(String id0, String id1, Message message) {
		Chat chat = repository.findChatBetween(id0, id1).orElseGet(() -> {
			Chat newChat = new Chat();
			newChat.setUser0Id(id0);
			newChat.setUser1Id(id1);
			return newChat;
		});

		boolean isValidAuthor = methodAuxiliary(chat, message);
		if (!isValidAuthor) {
			throw new MessageAuthorNotInChatException(
					"Author with ID " + message.getAuthorMsg().getId() + " does not belong to this chat");
		}
		chat.getMessages().add(message);
		chat.setFirstChat();
		chat.setLastActivity(message.getMommentMsg());
		return repository.save(chat);
	}

	public Chat findBetweenUsersId(String id01, String id02) {
		userRepository.findById(id01).orElseThrow(()
				-> new NotFoundObjectException("Id:" + id01 + " not found"));
		userRepository.findById(id02).orElseThrow(()
				-> new NotFoundObjectException("Id:" + id02 + " not found"));

		return repository.findChatBetween(id01, id02).orElseGet(() -> {
			throw new ChatBetweenUsersNotFoundException("those users don't have a common chat. ID1:" + id01 + " ID2:" + id02);
		});
		
	}
	public List<Message> searchMessage(String text) {
		List<Chat> chats = repository.searchByMessage(text);
		
		return chats.stream()
    			.flatMap(p -> p.getMessages().stream()
    					.filter(m-> m.getText().toLowerCase()
    							.contains(text.toLowerCase()))).collect(Collectors.toList());
    }	
	
	private boolean methodAuxiliary(Chat chat, Message message) {
		User user0 = userRepository.findById(chat.getUser0Id())
				.orElseThrow(() -> new NotFoundObjectException("Id:" + chat.getUser0Id() + " not found"));

		User user1 = userRepository.findById(chat.getUser1Id())
				.orElseThrow(() -> new NotFoundObjectException("Id:" + chat.getUser1Id() + " not found"));

		String id = message.getAuthorMsg().getId();

		boolean isValidAuthor = id.equals(user0.getId()) || id.equals(user1.getId());
		return isValidAuthor;
	}

}
