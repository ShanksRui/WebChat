package com.development.webchat.model.entities.DTO;

import com.development.webchat.model.entities.Status;
import com.development.webchat.model.entities.User;

public class UserSaveDTO {

	private String name;
	private Status status;
	private String passwor;

	public UserSaveDTO() {

	}

	public UserSaveDTO(User user) {
		this.name = user.getName();
		this.status = user.getStatus();
		this.passwor = user.getPassword();
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public String getPasswor() {
		return passwor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPasswor(String passwor) {
		this.passwor = passwor;
	}

	public UserSaveDTO(String name, String passwor) {
		super();
		this.name = name;
		this.passwor = passwor;
	}
}
