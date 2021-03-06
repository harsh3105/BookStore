package com.bookApp.api.order.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bookApp.api.order.model.Orders;

public interface OrderRepository extends CrudRepository<Orders,UUID> {
	
	@AllowFiltering
	@Query(allowFiltering = true)
	List<Orders> findByuserid(UUID id);
	
	@AllowFiltering
	Orders findByorderid(UUID id);

	void deleteByorderid(UUID orderid);

}
