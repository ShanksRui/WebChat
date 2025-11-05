package com.development.webchat.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndex(name = "users_Chat",def = "{'idChat': 1,'idUser': 1}",unique = true)
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private List<User> users = new ArrayList<>();
	private Instant lastActivity;
	private Instant firstChat;
	private List<Menssage> menssages = new ArrayList<>();
	
	
	public Chat() {
		
	}
	
	public Chat(String id, Instant lastActivity, Instant firstChat) {
		this.id = id;
		this.lastActivity = lastActivity;
		this.firstChat = firstChat;
	}
	public String getId() {
		return id;
	}
	public List<User> getUsers() {
		return users;
	}
	public Instant getLastActivity() {
		return lastActivity;
	}
	public Instant getFirstChat() {
		return firstChat;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void setLastActivity(Instant lastActivity) {
		this.lastActivity = lastActivity;
	}
	public void setFirstChat(Instant firstChat) {
		this.firstChat = firstChat;
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
		Chat other = (Chat) obj;
		return Objects.equals(id, other.id);
	}

	public List<Menssage> getMenssages() {
		return menssages;
	}

	public void setMenssages(List<Menssage> menssages) {
		this.menssages = menssages;
	}
	
}
