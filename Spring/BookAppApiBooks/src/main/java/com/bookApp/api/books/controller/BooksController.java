package com.bookApp.api.books.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookApp.api.books.model.Book;
import com.bookApp.api.books.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	private BookRepository repo;
	
	@GetMapping("/check")
	public String check() {
		return "working";
	}
	
	@PostMapping(value="/addBook")
	public void addBook(@RequestBody Book book){
		System.out.print(book.getBookId()+"skh");
		repo.save(book);
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
	
	@GetMapping("/getAllBook")
	public List<Book> getAllBooks(){
		List<Book> books = repo.findAll();
		List<Book> bookstosend = new ArrayList<>();
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getQuantity()>0) {
				bookstosend.add(books.get(i));
			}
		}
		return bookstosend;
	}
	
	@GetMapping("/orderBook")
	public void orderBook(@RequestParam("id") String BookId) {
		Optional<Book> book=repo.findById(BookId);
		Book book1  = book.get();
		book1.setQuantity(book1.getQuantity()-1);
		repo.deleteById(BookId);
		repo.save(book1);
	}
	
	@PostMapping(value="/orderCart")
	public void orderCart(@RequestBody Map<String,String> map) throws Exception{
		//HashMap<String,String> map = new ObjectMapper().readValue(string, HashMap.class);
		Set<String> set = map.keySet();
		for(String e: set) {
			Book book = repo.findById(e).get();
			int c= Integer.parseInt(map.get(e));
			book.setQuantity(book.getQuantity()-c);
			repo.save(book);
		}
//		return ResponseEntity.accepted().body("done");
	}
	
}
