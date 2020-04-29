package com.authentication.payload.response;

public class ForgetpasswordResponse {
	
	private String token;

	
	public ForgetpasswordResponse(String token) {
		
		this.token = token;
		
	}
	
	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	 

}
