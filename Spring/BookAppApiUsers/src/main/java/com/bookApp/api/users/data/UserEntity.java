package com.bookApp.api.users.data;

import java.io.Serializable;



import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.LocalDate;

@Table
public class UserEntity implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8391282942528778649L;
	 
	@PrimaryKey
	@Column
	private String userID;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String name;
	@Column
	private String phone;
	@Column
	private String encryptedPassword;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

}
