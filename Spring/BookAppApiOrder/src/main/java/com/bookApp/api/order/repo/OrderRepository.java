package com.bookApp.api.order.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.bookApp.api.order.model.Orders;

public interface OrderRepository extends CassandraRepository<Orders,Integer> {

	Optional<Orders> findByorderid(UUID id);

	void deleteByorderid(UUID orderid);

}
