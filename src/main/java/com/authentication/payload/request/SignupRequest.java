package com.authentication.payload.request;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.*;



//this class is the request class of the Signup page
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String firstname;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String lastname;
    
    
    
    @NotBlank
    private String interest;
    
  
    private List<String> interestlist;
    
    private String token;
 
    public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public List<String> getInterestlist() {
		return interestlist;
	}

	public void setInterestlist(List<String> interestlist) {
		this.interestlist = interestlist;
	}

	public String getInterest() {
		return interest;
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

	@NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> roles;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
  
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
    
    public Set<String> getRoles() {
      return this.roles;
    }
    
    public void setRole(Set<String> roles) {
      this.roles = roles;
    }
}
