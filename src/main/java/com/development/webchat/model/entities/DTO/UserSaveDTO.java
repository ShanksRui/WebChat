package com.development.webchat.model.entities.DTO;

import java.util.Objects;

import org.hibernate.validator.constraints.NotBlank;

import com.development.webchat.model.entities.Status;
import com.development.webchat.model.entities.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserSaveDTO {

	
    @NotBlank(message = "the name cannot be empty")
    @Size(min = 3,max = 20,message = "the name must be to have between 3 and 20 characters ")
	private String name;
    
    @NotNull(message = "status cannot be null")
	private Status status;
    
    @NotBlank(message = "the password must be mandatory")
    @Size(min = 5,max = 20,message = "password must be least 3 characters")
	private String password;

	public UserSaveDTO() {

	}

	public UserSaveDTO(User user) {
		this.name = user.getName();
		this.status = user.getStatus();
		this.password = user.getPassword();
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSaveDTO other = (UserSaveDTO) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

}
