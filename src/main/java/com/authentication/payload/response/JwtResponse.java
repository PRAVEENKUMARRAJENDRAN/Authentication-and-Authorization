package com.authentication.payload.response;

import java.util.List;

public class JwtResponse {
	//private String token;
	//private String type = "Bearer";
	private String id;
	private String username;
	private String interest;
	private String firstname;
	
	private String lastname;
	private String email;
	private List<String> roles;
	private List<String> interestlist;

	public JwtResponse( String id,  String username,String interest,List<String> interestlist, String firstname, String lastname, String email, List<String> roles) {
	//	this.token = accessToken;
		this.id = id;
		this.username = username;
		this.interest=interest;
		this.interestlist=interestlist;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email = email;
		this.roles = roles;
	}

	public String getInterest() {
		return interest;
	}

	public List<String> getInterestlist() {
		return interestlist;
	}

	public void setInterestlist(List<String> interestlist) {
		this.interestlist = interestlist;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


/* public String getTokenType() {
		return type;
}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	} 
	********/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
