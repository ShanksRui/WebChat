package com.development.webchat.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.development.webchat.model.entities.Chat;

public interface ChatRepository extends MongoRepository<Chat, String>{

	@Query("{ $or: [ { 'user0Id': ?0 ,'user1Id': ?1 }, { 'user0Id': ?1, 'user1Id': ?0 } ] }")
	Optional<Chat> findChatBetween(String id0,String id1);
}
