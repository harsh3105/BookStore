package com.bookApp.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookApp.api.users.shared.UserDTO;

public interface UsersService extends UserDetailsService{
	
	UserDTO createUser(UserDTO userdetails);
	
	UserDTO getUserDetailsByUsername(String username);
	
	UserDTO updateUser(UserDTO userdetails);
	
	void deleteUser(String username);
	
	UserDTO getUser(String username);

}
