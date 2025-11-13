package com.development.webchat.model.entities.DTO;

import java.util.Objects;

import com.development.webchat.model.entities.Status;

public class UserUpdateDTO {

	private String id;
	private String name;
	private Status status;
	private String passwor;
	
	public UserUpdateDTO() {
		
	}
	
	public UserUpdateDTO(String id, String name, Status status, String passwor) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.passwor = passwor;
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

	public String getPasswor() {
		return passwor;
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

	public void setPasswor(String passwor) {
		this.passwor = passwor;
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
