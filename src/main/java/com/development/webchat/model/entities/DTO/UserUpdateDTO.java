package com.development.webchat.model.entities.DTO;

import java.util.Objects;

import com.development.webchat.model.entities.Status;
import com.development.webchat.model.entities.User;

public class UserUpdateDTO {

	private String id;
	private String name;
	private Status status;
	private String password;
	
	public UserUpdateDTO() {
		
	}
	
	public UserUpdateDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.status = user.getStatus();
		this.password = user.getPassword();
	}

	public String getId() {
		return id;
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

	public void setId(String id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserUpdateDTO other = (UserUpdateDTO) obj;
		return Objects.equals(id, other.id);
	}
}
