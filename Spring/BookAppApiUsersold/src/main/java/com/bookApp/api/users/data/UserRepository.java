package com.bookApp.api.users.data;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<UserEntity, Long> {

}
