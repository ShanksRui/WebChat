package com.development.webchat.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatRepository, String>{

}
