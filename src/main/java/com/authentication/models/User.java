package com.authentication.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


//In this file we get the user signup field 
@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;
  
  @NotBlank
  @Size(max = 20)
  private String firstname;
  
  @NotBlank
  @Size(max = 20)
  private String lastname;
  

@NotBlank
  private String interest;
  
  List<String> interestlist = new ArrayList<>();
  
  
  

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @DBRef
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String firstname, String lastname,String interest,List<String> interestlist, String email, String password) {
    this.username = username;
    this.firstname=firstname;
    this.lastname=lastname;
    this.interest=interest;
    this.interestlist=interestlist;
    this.email = email;
    this.password = password;
  }

  public String getInterest() {
	return interest;
}

public void setInterest(String interest) {
	this.interest = interest;
}
public List<String> getInterestlist() {
	return interestlist;
}

public void setInterestlist(List<String> interestlist) {
	this.interestlist = interestlist;
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

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}
