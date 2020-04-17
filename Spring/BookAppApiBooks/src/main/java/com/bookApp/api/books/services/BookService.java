package com.bookApp.api.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookApp.api.books.model.Books;

@Service
public interface BookService {
	
	void save(Books book);

	void deleteById(String bookId);

	Optional<Books> findById(String bookId);

	List<Books> findAll();
}
