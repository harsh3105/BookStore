package com.bookApp.api.users.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookApp.api.users.model.UserEntity;
import com.bookApp.api.users.model.UserRequestModel;
import com.bookApp.api.users.model.UserResponseModel;
import com.bookApp.api.users.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@PostMapping("/addUser")
	public void createUser(@RequestBody UserRequestModel user) {
		UserEntity entity = new UserEntity();
		entity.setAddress(user.getAddress());
		entity.setEmail(user.getEmail());
		entity.setEncryptedPassword(user.getPassword());
		entity.setName(user.getName());
		entity.setPhone(user.getPhone());
		entity.setUsername(user.getUsername());
		entity.setUserID(UUID.randomUUID().toString());
		repo.save(entity);
	}
	
	@PostMapping("/updateUser")
	public void updateUser(@RequestBody UserRequestModel user) {
		UserEntity entity = repo.findByUsername(user.getUsername());
		UUID id = UUID.fromString(entity.getUserID());
		entity.setAddress(user.getAddress());
		entity.setEmail(user.getEmail());
		entity.setEncryptedPassword(user.getPassword());
		entity.setName(user.getName());
		entity.setPhone(user.getPhone());
		entity.setUserID(id.toString());
		repo.deleteById(id);
		repo.save(entity);
	}
	
	@DeleteMapping("/deleteUser")
	public void deleteUser( @RequestParam("username") String username) {
		UserEntity entity = repo.findByUsername(username);
		repo.deleteByUserID(entity.getUserID());
	}
	
	@GetMapping("/getUser")
	public UserResponseModel getUser(@RequestParam("username") String username) {
		UserEntity entity = repo.findByUsername(username);
		UserResponseModel user = new UserResponseModel();
		user.setAddress(entity.getAddress());
		user.setEmail(entity.getEmail());
		user.setName(entity.getName());
		user.setPassword(entity.getEncryptedPassword());
		user.setPhone(entity.getPhone());
		user.setUserid(entity.getUserID());
		user.setUsername(entity.getUsername());
		return user;
	}
	
	@GetMapping("/getUsername")
	public String getUsername(@RequestParam("id") String id) {
		UserEntity entity= repo.findByUserID(id);
		String username  =  entity.getUsername();
		return username;

	} 
}
