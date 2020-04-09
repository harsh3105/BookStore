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
		userdetails.setUserid(UUID.randomUUID().toString());
		userdetails.setEncryptedPassword(userdetails.getPassword());
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelmapper.map(userdetails, UserEntity.class);
		
		userEntity.setUserID(userdetails.getUserid());
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
		System.out.println(userEntity.getUserID());
		ModelMapper modelmap = new ModelMapper();
		UserDTO u = modelmap.map(userEntity,UserDTO.class);
		System.out.println(u.getUserid()+"harshu");
		u.setPassword(userEntity.getEncryptedPassword());
		return u;
	}



	@Override
	public UserDTO updateUser(UserDTO userdetails) {
	
		String id = getUserDetailsByUsername(userdetails.getUsername()).getUserid();
		System.out.println(id);
		repo.deleteByUserID(id);
		userdetails.setUserid(id);
		userdetails.setEncryptedPassword(userdetails.getPassword());
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = modelmapper.map(userdetails, UserEntity.class);
		userEntity.setUserID(id);
		
		repo.save(userEntity);
		
		UserDTO returnedValue = modelmapper.map(userEntity, UserDTO.class);
		
		return returnedValue;
	}



	@Override
	public void deleteUser(String username) {
		
		String id = getUserDetailsByUsername(username).getUserid();
		repo.deleteByUserID(id);
		
	}



	@Override
	public UserDTO getUser(String username) {
		return getUserDetailsByUsername(username);
		
		
	}



	@Override
	public String getUserbyID(String id) {
		UUID uid = UUID.fromString(id);
		UserEntity a = repo.findByuserID(id);
		return a.getUsername();
	}
	
	

}
