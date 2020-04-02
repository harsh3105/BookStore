package com.bookApp.api.order.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Orders {
	
	@PrimaryKey
	
	private UUID orderid;
	private UUID userid;
	private Map<String,String> bookIds;
	
	
	public UUID getOrderid() {
		return orderid;
	}
	public void setOrderid(UUID orderid) {
		this.orderid = orderid;
	}
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
	public Map<String, String> getBookIds() {
		return bookIds;
	}
	public void setBookIds(Map<String, String> bookIds) {
		this.bookIds = bookIds;
	}
	
	

}
