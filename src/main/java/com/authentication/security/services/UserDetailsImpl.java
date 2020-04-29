package com.authentication.security.services;

import java.util.Collection;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.authentication.models.*;
import com.fasterxml.jackson.annotation.JsonIgnore;



//In this class we get the data from the db of the user collection. 

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String firstname;
	
	private String lastname;
	

	private String username;
	private String interest;

	private String email;

	@JsonIgnore
	private String password;
	
	private List<String> interestlist;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String firstname,String lastname,String interest,List<String> interestlist, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstname=firstname;
		this.lastname=lastname;
		this.interest=interest;
		this.interestlist=interestlist;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities; ///we will be given a authority key and token 
	}

	public static UserDetailsImpl build(com.authentication.models.User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getFirstname(),
				user.getLastname(),
				user.getInterest(),
				user.getInterestlist(),
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	public List<String> getInterestlist() {
		return interestlist;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}


	public String getInterest() {
		return interest;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}