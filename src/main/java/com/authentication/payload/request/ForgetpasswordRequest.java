package com.authentication.payload.request;

import javax.validation.constraints.NotBlank;

public class ForgetpasswordRequest {
	
	//24-4-2020 setting a forget password request.
/*	@NotBlank
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	} */
	
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	




}
