package com.bookApp.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookApp.api.users.service.UsersService;
import com.bookApp.api.users.shared.UserDto;
import com.bookApp.api.users.ui.models.CreateUserRequestModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsersService userService;
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Working on port" + env.getProperty("local.server.port");
	}

	@PostMapping
	public String createuser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		
		
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userdto = modelmapper.map(userDetails, UserDto.class);
				
		userService.createUser(userdto);
		return "create user method is called";
	}
}
