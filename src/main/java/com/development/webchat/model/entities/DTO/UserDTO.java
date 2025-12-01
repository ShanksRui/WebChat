package com.development.webchat.model.entities.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.Status;
import com.development.webchat.model.entities.User;

public class UserDTO {


	private String id;
	private String name;
	private Status status;
	private List<String> chatsID = new ArrayList<>();
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.status = user.getStatus();
		this.chatsID = getIds(user.getChats());
		
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
	public List<String> getChatsID() {
		return chatsID;
	}
	public List<String> getIds(List<Chat> chats){
		List<String> ids = chats.stream().map(Chat::getId).collect(Collectors.toList());
		return ids;
	}
}
