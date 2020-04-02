package com.bookApp.api.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bookApp.api.order.model.Cart;
import com.bookApp.api.order.model.Orders;
import com.bookApp.api.order.repo.CartRepository;
import com.bookApp.api.order.repo.OrderRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/cart")
public class CartController {

	private static final String ORDER_BOOK_ENDPOINT_URL = "http://localhost:8011/books-ws/books/orderCart";
	private static final Object String = null;

	@Autowired
	CartRepository repo;

	@Autowired
	OrderRepository orderrepo;

	@PostMapping("/add")
	public void addCart(@RequestParam("id") String userId, @RequestParam("bookid") String bookid,
			@RequestParam("count") String count) {

		UUID userid = UUID.fromString(userId);
		try {
			Cart cart = repo.findById(userid).get();
			Map<String, String> map = cart.getBookquantity();
			if (!map.containsKey(bookid)) {
				map.put(bookid, count);
				cart.setBookquantity(map);
				repo.save(cart);
			} else {
				System.out.println(Integer.toString(Integer.parseInt(map.get(bookid)) + Integer.parseInt(count)));
				map.put(bookid, Integer.toString(Integer.parseInt(map.get(bookid)) + Integer.parseInt(count)));
				cart.setBookquantity(map);
				repo.save(cart);
			}
		} catch (Exception e) {
			Cart cart = new Cart();
			cart.setUserid(userid);
			Map<String, String> map = new HashMap<>();
			if (!map.containsKey(bookid)) {
				map.put(bookid, count);
				cart.setBookquantity(map);
				repo.save(cart);
			}
		}

	}

	@DeleteMapping("/emptyCart")
	public void emptyCart(@RequestParam("id") String userId) {
		UUID userid = UUID.fromString(userId);
		repo.deleteById(userid);

	}

	@GetMapping("/orderCart")
	public void orderCart(@RequestParam("id") String userId) {
		UUID userid = UUID.fromString(userId);
		Cart cart = repo.findById(userid).get();
		Orders order = new Orders();
		order.setUserid(userid);
		order.setOrderid(UUID.randomUUID());
		Map<String, String> map = new HashMap<>();
		map = cart.getBookquantity();
		order.setBookIds(map);
		orderrepo.save(order);
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(map, headers);
		ResponseEntity<Object> responseEntity = rt.exchange(ORDER_BOOK_ENDPOINT_URL, HttpMethod.POST, entity,
				Object.class);
		repo.deleteById(userid);
	}
	
	@GetMapping("/getCart")
	public Map<String,String> getCart(@RequestParam("id") String userId) {
		UUID userid = UUID.fromString(userId);
		Cart cart = repo.findById(userid).get();
		return cart.getBookquantity();
	}
	
	@DeleteMapping("/deletefromcart")
	public String deleteFromCart(@RequestParam("id") String userId,@RequestParam("bookid") String bookid) {
		UUID userid = UUID.fromString(userId);
		Cart cart = repo.findById(userid).get();
		Map<String,String> map = cart.getBookquantity();
		map.remove(bookid);
		cart.setBookquantity(map);
		repo.save(cart);
		return null;
	}
}
