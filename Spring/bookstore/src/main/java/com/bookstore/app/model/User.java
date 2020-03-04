package com.bookstore.app.model;


import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.utils.UUIDs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@PrimaryKey
	private UUID id;
	private String name;
	private String password;
	private String username;
	private String email;
	private Date dob;
	@Transient
	private String confirmPassword;
	
	public User() {}
	
	public User(String name, String password, String username, String email, Date dob) {
		super();
		this.name = name;
		this.password = password;
		this.username = username;
		this.email = email;
		this.dob = dob;
		this.id = UUIDs.timeBased();
	}
	
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
