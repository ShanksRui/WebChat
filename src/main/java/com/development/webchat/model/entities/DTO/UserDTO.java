package com.development.webchat.model.entities.DTO;

import java.util.Objects;

import com.development.webchat.model.entities.Status;

public class UserDTO {

	
	private String id;
	private String name;
	private Status status;
	
	public UserDTO(String id, String name, Status status) {
		this.id = id;
		this.name = name;
		this.status = status;
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
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStatus(Status status) {
		this.status = status;
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
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}
}
