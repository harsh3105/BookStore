package com.bookApp.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookApp.api.users.data.UserEntity;
import com.bookApp.api.users.data.UserRepository;
import com.bookApp.api.users.shared.UserDto;


@Service
public class UserServiceImpl implements UsersService{
	
	
	UserRepository rep;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepositroy) {
		
		this.rep = userRepositroy;
	}


	@Override
	public UserDto createUser(UserDto Userdetails) {
		
		Userdetails.setUserID(UUID.randomUUID().toString());
		
		
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userentity = modelmapper.map(Userdetails, UserEntity.class);
		
		userentity.setEncryptedPassword("test");
		
		rep.save(userentity);
		return null;
		
		
	}

}
