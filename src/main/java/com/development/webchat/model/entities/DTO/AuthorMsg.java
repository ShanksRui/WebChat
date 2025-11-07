package com.development.webchat.model.entities.DTO;

import java.util.Objects;

import com.development.webchat.model.entities.User;

public class AuthorMsg {

	private String id;
	private String name;
	
	public AuthorMsg() {
		
	}
	
	public AuthorMsg(User user) {
		this.id = user.getId();
		this.name = user.getName();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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
		AuthorMsg other = (AuthorMsg) obj;
		return Objects.equals(id, other.id);
	}

}
