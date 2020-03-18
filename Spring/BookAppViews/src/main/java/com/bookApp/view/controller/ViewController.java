package com.bookApp.view.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.bookApp.view.model.Book;
import com.bookApp.view.model.User;
import com.bookApp.view.model.UserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class ViewController {
	
	private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users";
	private static final String LOGIN_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/login";
	private static final String ADD_BOOK_ENDPOINT_URL ="http://localhost:8011/books-ws/books/addBook";
	private static final String GET_BOOK_ENDPOINT_URL ="http://localhost:8011/books-ws/books/getBook?id=";
	private static final String DELETE_BOOK_ENDPOINT_URL ="http://localhost:8011/books-ws/books/deleteBook?id=";
	
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
//	Signup page
	
	@GetMapping("/signup")
	public String showSignUp(User user,Model model){
		model.addAttribute("user", user);
		return "signuppage";
	}
	
	@PostMapping(value="/add", produces = "application/json")
	private String add(User user) {
		
		RestTemplate rt = new RestTemplate();
		String json = gson.toJson(user);
		System.out.print(json);
		User e = rt.postForObject(CREATE_USER_ENDPOINT_URL, user, User.class);
		return "redirect:view-ws/login";
	}
	
//	Login page
	
	@GetMapping("/login")
	public String ShowLogin(UserLogin details,Model model,BindingResult bindingResult) {
		model.addAttribute("details", details);
		return "loginpage";
	}
	
	@PostMapping(value="/check",produces = "application/json")
	private String login(UserLogin details) {
		RestTemplate rt = new RestTemplate();
		UserLogin u = rt.postForObject(LOGIN_USER_ENDPOINT_URL, details, UserLogin.class); 
		return "done";
	}
	
//	Admin home page
	
	@GetMapping(value="/admin/home")
	public String showAdminHome() {
		return "admin-home";
	}
	
	
//	Admin add book
	
	@GetMapping(value="/admin/addBook")
	public String showAddBook(Book book) {
		return "admin-add-book";
	}
	
	@PostMapping(value="/admin/adding",produces = "application/json")
	public String AddBook(Book book,Model model, BindingResult bindingResult) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject(ADD_BOOK_ENDPOINT_URL, book, Book.class);
		return "redirect:http://localhost:8011/views-ws/admin/home";
		
	}
	
//	Admin delete Book
	
	@GetMapping(value="/admin/deleteBook")
	public String showDeleteBook(Book book) {
		return "admin-delete-book";
	}
	
	@PostMapping(value="/admin/findBook")
	public String findBook(Book book,Model model) {
		RestTemplate rt = new RestTemplate();
		Book book1 = rt.getForObject(GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-delete-book";
	}
	
	@GetMapping(value="/admin/deleting")
	public String deleteBook(@RequestParam("id") String id) {
		RestTemplate rt = new RestTemplate();
		rt.delete(DELETE_BOOK_ENDPOINT_URL+id);
		return "redirect:http://localhost:8011/views-ws/admin/home";
		
	}
	
//	Admin get Book details
	
	@GetMapping(value="/admin/getBook")
	public String showGetBook(Book book) {
		return "admin-view-book";
	}
	
	@PostMapping(value="/admin/findBook1")
	public String findBook1(Book book,Model model) {
		RestTemplate rt = new RestTemplate();
		Book book1 = rt.getForObject(GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-view-book";
	}
	
//	Admin update Book details
	
	@GetMapping(value="/admin/updateBook")
	public String showUpdateBook(Book book) {
		return "admin-update-book";
	}
	
	@PostMapping(value="/admin/findBook2")
	public String findBook2(Book book,Model model) {
		RestTemplate rt = new RestTemplate();
		Book book1 = rt.getForObject(GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-update-book";
	}

}
