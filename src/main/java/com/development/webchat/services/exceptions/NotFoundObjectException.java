package com.development.webchat.services.exceptions;

public class NotFoundObjectException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NotFoundObjectException(String msg) {
		super(msg);
	}

}
