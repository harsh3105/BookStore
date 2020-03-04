package com.bookApp.api.users.service;


import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookApp.api.users.data.UserEntity;
import com.bookApp.api.users.data.UserRepository;
import com.bookApp.api.users.shared.UserDTO;


@Service
public class UserServiceImpl implements UsersService{
	
	UserRepository repo;
	
	BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Autowired
	public UserServiceImpl(UserRepository repo,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repo = repo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}



	@Override
	public UserDTO createUser(UserDTO userdetails) {
		userdetails.setUserID(UUID.randomUUID().toString());
		userdetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userdetails.getPassword()));
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelmapper.map(userdetails, UserEntity.class);
		
		
		repo.save(userEntity);
		
		UserDTO returnedValue = modelmapper.map(userEntity, UserDTO.class);
		
		return returnedValue;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity=repo.findByUsername(username);
		if(userEntity==null) throw new UsernameNotFoundException(username);
		return new User(userEntity.getUsername(),userEntity.getEncryptedPassword(), true , true, true , true , new ArrayList<>());
	}



	@Override
	public UserDTO getUserDetailsByUsername(String username) {
		UserEntity userEntity=repo.findByUsername(username);
		if(userEntity==null) throw new UsernameNotFoundException(username);
		
		
		return new ModelMapper().map(userEntity,UserDTO.class);
	}

}
