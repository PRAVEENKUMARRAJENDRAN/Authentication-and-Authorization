package com.authentication.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "token")

public class Token {
	  @Id
	  private String id;

	  @NotBlank
	  @Size(max = 20)
	  private String username;
	  
	  

	private String token;
	  

	  
	  public Token() {
		  
	  }
	  
	  public Token(String username,String email ) {
		  this.username=username;
		  this.email=email;
		  
		 
	  }


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}
	  private String email;
	  
	  
	  public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	  
	  
}
