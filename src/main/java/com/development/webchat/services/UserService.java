package com.development.webchat.services;

import org.springframework.stereotype.Service;

import com.development.webchat.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
}
