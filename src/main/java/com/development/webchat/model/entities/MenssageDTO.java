package com.development.webchat.model.entities;
import java.time.Instant;

import com.development.webchat.model.entities.DTO.AuthorMsg;

public class MenssageDTO  {
	
    private AuthorMsg authorMsg;
    private String menssage;
    private Instant mommentMsg;
    
    public MenssageDTO() {
    	
    }
    
	public MenssageDTO(AuthorMsg authorMsg, String menssage, Instant mommentMsg) {
		this.authorMsg = authorMsg;
		this.menssage = menssage;
		this.mommentMsg = mommentMsg;
	}
	public AuthorMsg getAuthorMsg() {
		return authorMsg;
	}
	public String getMenssage() {
		return menssage;
	}
	public Instant getMommentMsg() {
		return mommentMsg;
	}
	public void setAuthorMsg(AuthorMsg authorMsg) {
		this.authorMsg = authorMsg;
	}
	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}
	public void setMommentMsg(Instant mommentMsg) {
		this.mommentMsg = mommentMsg;
	}
      
}
