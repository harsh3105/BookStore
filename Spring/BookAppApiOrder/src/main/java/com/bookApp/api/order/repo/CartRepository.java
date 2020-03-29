package com.bookApp.api.order.repo;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.bookApp.api.order.model.Cart;



public interface CartRepository extends CassandraRepository<Cart,UUID> {

}
