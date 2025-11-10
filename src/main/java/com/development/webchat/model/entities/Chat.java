package com.development.webchat.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.development.webchat.model.entities.DTO.Message;;

@Document
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@DBRef(lazy = true)
	private Set<User> participants = new HashSet<>();
	private Instant lastActivity;	
	private Instant firstChat;
	
	private List<Message> menssages = new ArrayList<>();
	
	public Chat() {
		
	}
	
	public Chat(String id) {
		this.id = id;
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
	   this.lastActivity = flowDateLast(lastActivity);
	}
	
	public void setFirstChat(Instant firstChat) {
		this.firstChat= initialMsg(firstChat); 	
		
	}
	
	public Instant flowDateLast(Instant last) {	
		Instant temp = menssages.stream().map(Message::getMommentMsg)
				 .filter(Objects::nonNull)
				 .max(Instant::compareTo).orElse(null);
		
		if(last.equals(temp)){
			return last;
		}else {
			throw new IllegalArgumentException("argument invalid");
		}
	}
	public Instant initialMsg(Instant first) {
		return first = menssages.stream().map(Message::getMommentMsg)
				 .filter(Objects::nonNull)
				 .min(Instant::compareTo).orElse(null);
	}
	
	public Integer getTotalMsg() {
	  return menssages.size();
	}
	
	public List<Message> getMenssages() {
		return menssages;
	}

	public Set<User> getParticipants() {
		return participants;
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
}
