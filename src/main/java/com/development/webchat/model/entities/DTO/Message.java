package com.development.webchat.model.entities.DTO;

import java.time.Instant;

public class Message  {
	
    private AuthorMsg authorMsg;
    private String text;
    private Instant mommentMsg;
    
    public Message() {
    	
    }
    
	public Message(AuthorMsg authorMsg, String text, Instant mommentMsg) {
		this.authorMsg = authorMsg;
		this.text = text;
		this.mommentMsg = mommentMsg;
	}
	public AuthorMsg getAuthorMsg() {
		return authorMsg;
	}
	
	public void setAuthorMsg(AuthorMsg authorMsg) {
		this.authorMsg = authorMsg;
	}
	
	public Instant getMommentMsg() {
		return mommentMsg;
	}

	public void setMommentMsg(Instant mommentMsg) {
		this.mommentMsg = mommentMsg;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
      
}
