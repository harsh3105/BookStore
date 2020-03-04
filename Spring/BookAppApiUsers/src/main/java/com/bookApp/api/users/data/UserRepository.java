package com.bookApp.api.users.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<UserEntity,UUID>{
	
	@AllowFiltering
	UserEntity findByUsername(String username);
}
