package com.bookApp.api.users.data;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface UserRepository extends CassandraRepository<UserEntity,UUID>{
	
	@AllowFiltering
	UserEntity findByUsername(String username);
	
	@AllowFiltering
	@Query(" DELETE FROM userentity WHERE userid=?0")
	void deleteByUserID(String id);
	
	UserEntity findByuserID(String id);
}
