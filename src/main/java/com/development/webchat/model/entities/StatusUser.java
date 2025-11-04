package com.development.webchat.model.entities;

public enum StatusUser {

	OFFLINE(1),
	ONLINE(2),
	DO_NOT_DISTURB(3),
	ABSENT(4);
	
	private final int code;
	
	private StatusUser(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
