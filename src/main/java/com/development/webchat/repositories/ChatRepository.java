package com.development.webchat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.development.webchat.model.entities.Chat;

public interface ChatRepository extends MongoRepository<Chat, String>{

}
