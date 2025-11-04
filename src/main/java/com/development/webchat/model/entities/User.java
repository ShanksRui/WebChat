package com.development.webchat.model.entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private StatusUser status;
	
	
	public User () {
		
	}
	
	public User(String id, String name, StatusUser status) {
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


	public StatusUser getStatus() {
		return status;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setStatus(StatusUser status) {
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
