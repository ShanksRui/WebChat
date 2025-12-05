package com.development.webchat.services.exceptions;

public class ChatBetweenUsersNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ChatBetweenUsersNotFoundException(String msg) {
		super(msg);
	}
}
