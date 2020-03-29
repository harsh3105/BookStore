package com.bookApp.api.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private static final String ORDER_BOOK_ENDPOINT_URL = "http://localhost:8011/books-ws/books/orderBook?id=";
	private static final Object String = null;

	@Autowired
	CartRepository repo;
	
	@Autowired
	OrderRepository orderrepo;
	
	@PostMapping("/add")
	public void addCart(@RequestParam("id") String userId,@RequestParam("bookid") String bookid) {
		Cart cart = new Cart();
		UUID userid = UUID.fromString(userId);
		cart.setUserid(userid);
		Map<String,String> map = new HashMap<>();
		String[] bids = bookid.split(",");
		for(int i=0;i<bids.length;i++) {
			System.out.println(bids[i]);
			if(!map.containsKey(bids[i])){
				map.put(bids[i], "1");
			}
			cart.setBookquantity(map);
			repo.save(cart);
		}
	}
	
	@PostMapping("/inc")
	public void increaseQuantity(@RequestParam("bookid") String bookid, @RequestParam("id") String userid) {
		UUID userId = UUID.fromString(userid);
		Cart cart = repo.findById(userId).get();
		Map<String,String> map = new HashMap<>();
		map = cart.getBookquantity();
		int c = Integer.parseInt(map.get(bookid));
		c++;
		String count = Integer.toString(c);
		map.put(bookid, count);
		cart.setBookquantity(map);
		repo.save(cart);
		
	}
	
	@PostMapping("/dec")
	public void decreaseQuantity(@RequestParam("bookid") String bookid, @RequestParam("id") String userid) {
		UUID userId = UUID.fromString(userid);
		Cart cart = repo.findById(userId).get();
		Map<String,String> map = new HashMap<>();
		map = cart.getBookquantity();
		int c = Integer.parseInt(map.get(bookid));
		c--;
		if(c>0) {
			String count = Integer.toString(c);
			map.put(bookid, count);
			cart.setBookquantity(map);
		}
		else {
			map.remove(bookid);
		}
		repo.save(cart);
	}
	
	@DeleteMapping("/emptyCart")
	public void emptyCart(@RequestParam("id") String userId) {
		UUID userid = UUID.fromString(userId);
		repo.deleteById(userid);
		
	}
	
	
	@GetMapping("/orderCart")
	public void orderCart(@RequestParam("id") String userId) {
		UUID userid = UUID.fromString(userId);
		Cart cart= repo.findById(userid).get();
		Orders order = new Orders();
		order.setUserid(userid);
		order.setOrderid(UUID.randomUUID());
		Map<String,String> map = new HashMap<>();
		map = cart.getBookquantity();
		Set<String> items =   map.keySet();
		List<String> bookids = new ArrayList<>();
		for (String e : items ) {
			bookids.add(e);
		}
		order.setBookIds(bookids);
		orderrepo.save(order);
		RestTemplate rt = new RestTemplate();
		//rt.postForEntity(ORDER_BOOK_ENDPOINT_URL, String, String.class);
		
			
	}
}
