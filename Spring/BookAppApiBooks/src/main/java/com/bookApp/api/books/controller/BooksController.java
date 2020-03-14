package com.bookApp.api.books.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookApp.api.books.model.Book;
import com.bookApp.api.books.repository.BookRepository;

@RestController
public class BooksController {
	
	@Autowired
	private BookRepository repo;
	
	@PostMapping(value="/addBook", consumes="application/json")
	public String addBook(@RequestBody Book book){
		System.out.print(book.getBookId()+"skh");
		repo.save(book);
		return "done";
	}
	
	@DeleteMapping("/deleteBook")
	public void deleteBook(@RequestParam("id") String BookId){
		repo.deleteById(BookId);
	}
	
	@PostMapping("/updateBook")
	public void updateBook(@RequestBody Book book) {
		repo.deleteById(book.getBookId());
		repo.save(book);
	}
	
	@GetMapping("/getBook")
	public Optional<Book> getBook(@RequestParam("id") String BookId){
		Optional<Book> book=repo.findById(BookId);
		return book;
	}
}
