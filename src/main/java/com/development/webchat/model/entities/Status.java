package com.development.webchat.model.entities;

public enum Status {

	OFFLINE(1),
	ONLINE(2),
	DO_NOT_DISTURB(3),
	ABSENT(4);
	
	private final int code;
	
	private Status(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
