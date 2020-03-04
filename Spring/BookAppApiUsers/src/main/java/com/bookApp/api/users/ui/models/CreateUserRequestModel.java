package com.bookApp.api.users.ui.models;



import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CreateUserRequestModel {

	
	private String userId;
	@NotNull(message="username cannot be null")
	private String username;
	@NotNull(message="name cannot be null")
	private String name;
	@NotNull(message="date cannot be null")
	private Date dob;
	@NotNull(message="email cannot be null")
	@Email
	private String email;
	@NotNull(message="password cannot be null")
	@Size(min=8, max=30, message="Password should be greater than or equal to 8 character or less than 30 characaters")
	private String password;
	@NotNull(message="address cannot be null")
	private String address;
	@NotNull(message="phone no. cannot be null")
	private String phone;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
