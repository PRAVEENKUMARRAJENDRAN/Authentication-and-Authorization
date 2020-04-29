package com.authentication.payload.response;

public class DecryptedResponse {
	private String password;
	
	public DecryptedResponse(String password)
	{
		this.password=password;
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
