package com.bookApp.api.users.ui.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookApp.api.users.service.UsersService;
import com.bookApp.api.users.shared.UserDTO;
import com.bookApp.api.users.ui.models.CreateUserRequestModel;
import com.bookApp.api.users.ui.models.CreateUserResponseModel;
import com.datastax.driver.core.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService userservice;
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Working on port" + env.getProperty("local.server.port")+" , with token = " + env.getProperty("token.secret");
	
	}
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userdto = modelmapper.map(userDetails, UserDTO.class);
		UserDTO createdUser = userservice.createUser(userdto);
		
		CreateUserResponseModel returnValue = modelmapper.map(createdUser,CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}

}
