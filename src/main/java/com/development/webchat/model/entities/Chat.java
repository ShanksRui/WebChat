package com.development.webchat.model.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.development.webchat.model.entities.DTO.MenssageDTO;;

@Document
@CompoundIndex(name = "users_Chat",def = "{'idUser0': 1,'idUser1': 1}",unique = true)
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String idUserZ;
	private String idUserX;
	private Instant lastActivity;	
	private Instant firstChat;
	
	private List<MenssageDTO> menssages = new ArrayList<>();
	
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
	public void joinUser(User x,User z) {
		setIdUserX(x.getId());
		setIdUserZ(z.getId());
	}
	public Instant flowDateLast(Instant last) {	
		Instant temp = menssages.stream().map(MenssageDTO::getMommentMsg)
				 .filter(Objects::nonNull)
				 .max(Instant::compareTo).orElse(null);
		
		if(last.equals(temp)){
			return last;
		}else {
			throw new IllegalArgumentException("argument invalid");
		}
	}
	public Instant initialMsg(Instant first) {
		return first = menssages.stream().map(MenssageDTO::getMommentMsg)
				 .filter(Objects::nonNull)
				 .min(Instant::compareTo).orElse(null);
	}
	
	public List<MenssageDTO> getMenssages() {
		return menssages;
	}

	public void setMenssages(List<MenssageDTO> menssages) {
		this.menssages = menssages;
	}

	public String getIdUserZ() {
		return idUserZ;
	}

	public String getIdUserX() {
		return idUserX;
	}

	public void setIdUserZ(String idUserZ) {
		this.idUserZ = idUserZ;
	}

	public void setIdUserX(String idUserX) {
		this.idUserX = idUserX;
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
