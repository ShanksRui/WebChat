package com.development.webchat.services.exceptions;

public class MessageAuthorNotInChatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MessageAuthorNotInChatException(String message) {
		super(message);
	}
}
