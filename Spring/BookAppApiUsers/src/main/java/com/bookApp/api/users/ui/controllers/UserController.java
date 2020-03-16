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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

		return "Working on port" + env.getProperty("local.server.port") + " , with token = "
				+ env.getProperty("token.secret");

	}

	@PostMapping("/addUser")
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {

		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userdto = modelmapper.map(userDetails, UserDTO.class);
		UserDTO createdUser = userservice.createUser(userdto);

		CreateUserResponseModel returnValue = modelmapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);

	}

	@PostMapping("/updateUser")
	public ResponseEntity<CreateUserResponseModel> updateUser(@Valid @RequestBody CreateUserRequestModel userDetails) {

		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userdto = modelmapper.map(userDetails, UserDTO.class);
		UserDTO createdUser = userservice.updateUser(userdto);

		CreateUserResponseModel returnValue = modelmapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<CreateUserResponseModel> deleteUser(@Valid @RequestParam("username") String username) {

		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO createdUser = userservice.getUser(username);
		userservice.deleteUser(username);
		CreateUserResponseModel returnValue = modelmapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}
	
	@GetMapping("/getUser")
	public ResponseEntity<CreateUserResponseModel> getUser(@Valid @RequestParam("username") String username) {

		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO createdUser = userservice.getUser(username);
		CreateUserResponseModel returnValue = modelmapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);

	}

	
	


}
