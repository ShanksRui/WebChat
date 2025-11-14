package com.development.webchat.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.development.webchat.model.entities.DTO.Message;;

@Document
@CompoundIndex(name = "users_Chat", def = "{'user0Id': 1, 'user1Id': 1}", unique = true)
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    @Id
	    private String id;
	    private String user0Id;
	    private String user1Id;
	    private List<Message> messages = new ArrayList<>();
	    private Instant lastActivity;
	    private Instant firstChat;
	
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
		Instant temp = messages.stream().map(Message::getMommentMsg)
				 .filter(Objects::nonNull)
				 .max(Instant::compareTo).orElse(null);
		
		if(last.equals(temp)){
			return last;
		}else {
			throw new IllegalArgumentException("argument invalid");
		}
	}
	public Instant initialMsg(Instant first) {
		return first = messages.stream().map(Message::getMommentMsg)
				 .filter(Objects::nonNull)
				 .min(Instant::compareTo).orElse(null);
	}
	
	public Integer getTotalMsg() {
	  return messages.size();
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public String getUser0Id() {
		return user0Id;
	}

	public String getUser1Id() {
		return user1Id;
	}

	public void setUser0Id(String user0Id) {
		this.user0Id = user0Id;
	}

	public void setUser1Id(String user1Id) {
		this.user1Id = user1Id;
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
