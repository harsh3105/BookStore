package com.bookApp.api.books.model;

import java.util.HashMap;
import java.util.Map;

public class BooksQuantity {
	
	private Map<String,String> bookquantity = new HashMap<>();

	public Map<String,String> getBookquantity() {
		return bookquantity;
	}

	public void setBookquantity(Map<String,String> bookquantity) {
		this.bookquantity = bookquantity;
	}

}
