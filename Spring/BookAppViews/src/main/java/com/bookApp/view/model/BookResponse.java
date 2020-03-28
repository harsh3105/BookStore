package com.bookApp.view.model;

import java.util.ArrayList;
import java.util.List;

public class BookResponse {
	private List<Book> books;
	
	public BookResponse() {
		books = new ArrayList<>();
	}

	public BookResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
