package com.bookApp.api.order.model;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Cart {
	
	@PrimaryKey
	private UUID userid;
	private Map<String,String> bookquantity;
	
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
	public Map<String, String> getBookquantity() {
		return bookquantity;
	}
	public void setBookquantity(Map<String, String> bookquantity) {
		this.bookquantity = bookquantity;
	}

}
