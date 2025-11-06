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
@CompoundIndex(name = "users_Chat",def = "{'idUser0': 1,'idUser1': 1}",unique = true)
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String idUser0;
	private String idUser1;
	private Instant lastActivity;
	private Instant firstChat;
	private List<MenssageDTO> menssages = new ArrayList<>();
	
	
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
	public Instant getLastActivity() {
		return lastActivity;
	}
	public Instant getFirstChat() {
		return firstChat;
	}
	public void setId(String id) {
		this.id = id;
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

	public List<MenssageDTO> getMenssages() {
		return menssages;
	}

	public void setMenssages(List<MenssageDTO> menssages) {
		this.menssages = menssages;
	}

	public String getIdUser0() {
		return idUser0;
	}

	public void setIdUser0(String idUser0) {
		this.idUser0 = idUser0;
	}

	public String getIdUser1() {
		return idUser1;
	}

	public void setIdUser1(String idUser1) {
		this.idUser1 = idUser1;
	}
	
}
