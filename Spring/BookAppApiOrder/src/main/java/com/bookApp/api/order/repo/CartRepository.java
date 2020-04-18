package com.bookApp.api.order.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.bookApp.api.order.model.Cart;

public interface CartRepository extends CrudRepository<Cart,UUID>{

}
