package com.bookApp.view.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.bookApp.view.model.UserDetail;

public class UserDetailsServiceImp implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDetail user = findUserbyUername(username);
		UserBuilder builder =null;
		if(user!=null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRole());
			
		}
		else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}

	private UserDetail findUserbyUername(String username) {
		// TODO Auto-generated method stub
		if(username.equals("admin")) {
			UserDetail user = new UserDetail();
			user.setUsername(username);
			user.setPassword("admin");
			user.setRole("ADMIN");
			return user;
		}
		RestTemplate rt = new RestTemplate();
		UserDetail user = rt.getForObject("http://localhost:8011/users-ws/users/getUser?username="+username, UserDetail.class);
		user.setRole("USER");
		return user;
	}

}
