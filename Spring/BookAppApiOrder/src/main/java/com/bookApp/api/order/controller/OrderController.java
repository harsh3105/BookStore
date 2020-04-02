package com.bookApp.api.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bookApp.api.order.model.Orders;
import com.bookApp.api.order.repo.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderRepository repo;

	
	@GetMapping("/check")
	public void check() {
		System.out.print("working");
	}
	
	@PostMapping(value="/addOrder")
	public String addBook(@RequestParam("userid")  String userId, @RequestParam("bookid") String bookId){
		//System.out.print(reqorder.getUserid()+"skh");
		Orders order = new Orders();
		UUID id = UUID.randomUUID();
		order.setOrderid(id);
		UUID userid = UUID.fromString(userId);
		order.setUserid(userid);
		String[] s = bookId.split("=");
		Map<String,String> map = new HashMap<>();
		map.put(s[0], s[1]);
		order.setBookIds(map);
		repo.save(order);
		return "done";
	}
	
	@GetMapping("/getOrder")
	public Optional<Orders> getOrder(@RequestParam("id") UUID id){
		Optional<Orders> order=repo.findByorderid(id);
		return order;
	}
	
	@PostMapping("/updateOrder")
	public void updateBook(@RequestBody Orders order) {
		repo.deleteByorderid(order.getOrderid());
		repo.save(order);
	}
	
	@DeleteMapping("/deleteOrder")
	public void deleteOrder(@RequestParam("id") UUID id){
		repo.deleteByorderid(id);
	}
	

}
