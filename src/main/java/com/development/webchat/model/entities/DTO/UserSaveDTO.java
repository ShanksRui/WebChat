package com.development.webchat.model.entities.DTO;

import com.development.webchat.model.entities.Status;
import com.development.webchat.model.entities.User;

public class UserSaveDTO {

	private String name;
	private Status status;
	private String password;

	public UserSaveDTO() {

	}

	public UserSaveDTO(User user) {
		this.name = user.getName();
		this.status = user.getStatus();
		this.password = user.getPassword();
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserSaveDTO(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
}
