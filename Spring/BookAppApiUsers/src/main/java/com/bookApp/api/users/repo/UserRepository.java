package com.bookApp.api.users.repo;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookApp.api.users.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,UUID> {

	@AllowFiltering
	public UserEntity findByUsername(String username);
	
	@AllowFiltering
	@Query(" DELETE FROM userentity WHERE userid=?0")
	void deleteByUserID(String id);
	
	UserEntity findByUserID(String fromString);

}
