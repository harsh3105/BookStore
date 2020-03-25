package com.bookApp.api.order.model;

import java.util.List;
import java.util.UUID;

public class RequestOrders {
	
	
	private UUID userid;
	private List<String> bookIds;
	
	
	
	public UUID getUserid() {
		return userid;
	}
	public void setUserid(UUID userid) {
		this.userid = userid;
	}
	public List<String> getBookIds() {
		return bookIds;
	}
	public void setBookIds(List<String> bookId) {
		this.bookIds = bookId;
	}

}
