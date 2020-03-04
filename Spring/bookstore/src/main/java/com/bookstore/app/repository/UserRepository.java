package com.bookstore.app.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;


import com.bookstore.app.model.User;

public interface UserRepository extends CassandraRepository<User,UUID> {

	@AllowFiltering
	User findByUsername(String username);

}
