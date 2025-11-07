package com.development.webchat.model.entities.DTO;

import java.time.Instant;

public class MenssageDTO  {
	
    private AuthorMsg authorMsg;
    private String text;
    private Instant mommentMsg;
    
    public MenssageDTO() {
    	
    }
    
	public MenssageDTO(AuthorMsg authorMsg, String text, Instant mommentMsg) {
		this.authorMsg = authorMsg;
		this.text = text;
		this.mommentMsg = mommentMsg;
	}
	public AuthorMsg getAuthorMsg() {
		return authorMsg;
	}
	public String getMenssage() {
		return text;
	}
	public Instant getMommentMsg() {
		return mommentMsg;
	}
	public void setAuthorMsg(AuthorMsg authorMsg) {
		this.authorMsg = authorMsg;
	}
	public void setMenssage(String text) {
		this.text = text;
	}
	public void setMommentMsg(Instant mommentMsg) {
		this.mommentMsg = mommentMsg;
	}
      
}
