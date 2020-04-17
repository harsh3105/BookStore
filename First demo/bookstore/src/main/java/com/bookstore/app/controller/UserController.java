package com.bookstore.app.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.app.model.User;
import com.bookstore.app.model.UserLogin;
import com.bookstore.app.repository.UserRepository;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/welcome")
	public String done() {
		return "done";
	}
	
	@GetMapping("/signup")
	public String showsignup(User user) {
		return "signup";
	}
	
	@PostMapping("/add")
	public String createuser(@Valid User user,BindingResult bindingResult,Model model){
		if(user.getPassword().equals(user.getConfirmPassword())) {
			String encodedPassword = new String(new BCryptPasswordEncoder().encode(user.getPassword()));
			User u1 = new User(user.getName(),encodedPassword,user.getUsername(),user.getEmail(),user.getDob()); 
			repo.save(u1);
			return "redirect:welcome";
		}
		else {
			return "signup";
		}
		
	}
	@GetMapping("/login")
	public String showlogin(Model model) {
		model.addAttribute("userlogin",new UserLogin());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserLogin userlogin,BindingResult result) 
	{
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user1 = repo.findByUsername(userlogin.getUsername());
		if(encoder.matches(userlogin.getPassword(), user1.getPassword())) {
			System.out.println("yes");
			return "redirect:welcome";
		}
		else {
			return "login";
		}
	}

}
