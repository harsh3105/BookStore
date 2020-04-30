package com.bookApp.view.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookApp.view.model.UserDetail;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("inside load by username"+username);
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
		String a = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		//UserDetail user = rt.getForObject("http://users:2222/users/getUser?username="+username, UserDetail.class);
		UserDetail user = rt.getForObject(host+":7500/users/getUser?username="+username, UserDetail.class);
		user.setRole("USER");
		return user;
	}

}
