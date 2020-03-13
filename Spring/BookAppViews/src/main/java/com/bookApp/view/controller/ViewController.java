package com.bookApp.view.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.bookApp.view.model.User;
import com.bookApp.view.model.UserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class ViewController {
	
	private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users";
	private static final String LOGIN_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/login";
	
	@GetMapping("/signup")
	public String showSignUp(User user,Model model){
		model.addAttribute("user", user);
		return "signuppage";
	}
	
	@PostMapping(value="/add", produces = "application/json")
	private String add(User user) {
		
		RestTemplate rt = new RestTemplate();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(user);
		System.out.print(json);
		User e = rt.postForObject(CREATE_USER_ENDPOINT_URL, user, User.class);
		return "redirect:view-ws/login";
	}
	
	@GetMapping("/login")
	public String ShowLogin(UserLogin details,Model model,BindingResult bindingResult) {
		model.addAttribute("details", details);
		return "loginpage";
	}
	
	@PostMapping(value="/check",produces = "application/json")
	private String login(UserLogin details) {
		RestTemplate rt = new RestTemplate();
		UserLogin u = rt.postForObject(LOGIN_USER_ENDPOINT_URL, details, UserLogin.class); 
		return "done";
	}

}
