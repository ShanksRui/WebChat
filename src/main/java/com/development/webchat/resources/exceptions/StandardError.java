package com.development.webchat.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String msgError;
	private String path;

	public StandardError() {
		
	}
	
	public StandardError(Instant timestamp, Integer status, String error, String msgError, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.msgError = msgError;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMsgError() {
		return msgError;
	}

	public String getPath() {
		return path;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
