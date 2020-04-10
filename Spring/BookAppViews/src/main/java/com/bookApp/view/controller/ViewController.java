package com.bookApp.view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bookApp.view.model.Book;
import com.bookApp.view.model.CartResponse;
import com.bookApp.view.model.Order;
import com.bookApp.view.model.Quantity;
import com.bookApp.view.model.User;
import com.bookApp.view.model.UserDetail;
import com.bookApp.view.model.UserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Controller
public class ViewController {

	// admin
	private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/addUser";
	private static final String LOGIN_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/login";
	private static final String ADD_BOOK_ENDPOINT_URL = "http://localhost:8011/books-ws/books/addBook";
	private static final String GET_BOOK_ENDPOINT_URL = "http://localhost:8011/books-ws/books/getBook?id=";
	private static final String DELETE_BOOK_ENDPOINT_URL = "http://localhost:8011/books-ws/books/deleteBook?id=";
	private static final String GET_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/getUser?username=";
	private static final String DELETE_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/deleteUser?username=";
	private static final String UPDATE_USER_ENDPOINT_URL = "http://localhost:8011/users-ws/users/updateUser";
	private static final String GET_USERNAME_ENDPOINT_URL = "http://localhost:8011/users-ws/users/getUsername?id=";

	// user
	private static final String GET_ALL_BOOKS_ENDPOINT_URL = "http://localhost:8011/books-ws/books/getAllBook";
	private static final String ORDER_BOOK_ENDPOINT_URL = "http://localhost:8011/books-ws/books/orderBook?id=";
	private static final String GENERATE_ORDER_ENDPOINT_URL = "http://localhost:8011/orders-ws/orders/addOrder?userid=";
	private static final String ADD_TO_CART_ENDPOINT_URL = "http://localhost:8011/orders-ws/cart/add?id=";
	private static final String GET_USER_CART_ENDPOINT_URL = "http://localhost:8011/orders-ws/cart/getCart?id=";
	private static final String ORDER_CART_ENDPOINT_URL = "http://localhost:8011/orders-ws/cart/orderCart?id=";
	private static final String DELETE_BOOK_FROM_CART_ENDPOINT_URL = "http://localhost:8011/orders-ws/cart/deletefromcart?id=";
	private static final String GET_USER_ORDER_ENDPOINT_URL = "http://localhost:8011/orders-ws/orders/getOrder?id=";
	private static final String GET_ORDER_BOOKS_ENDPOINT_URL = "http://localhost:8011/orders-ws/orders/getBooks?orderid=";
	private static final String GET_ALL_ORDER_ENDPOINT_URL = "http://localhost:8011/orders-ws/orders/getAllOrders";
	private static final String GET_ORDER_USER_ENDPOINT_URL = "http://localhost:8011/orders-ws/orders/getOrderUser?id=";
	private static final Object String = null;

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

//	Signup page

	@GetMapping("/signup")
	public String showSignUp(User user, Model model) {
		model.addAttribute("user", user);
		return "signuppage";
	}

	@PostMapping(value = "/add", produces = "application/json")
	private String add(User user,HttpServletRequest request) {

		RestTemplate rt = new RestTemplate();
		String json = gson.toJson(user);
		System.out.print(json);
		User e = rt.postForObject(CREATE_USER_ENDPOINT_URL, user, User.class);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/login";
	}

//	Login page
	@GetMapping("/login")
	public String ShowLogin(@RequestParam(value = "error", required = false) String error) {
		System.out.println(error);
		return "loginpage";
	}
	
	

//	Admin home page

	@GetMapping(value = "/admin/home")
	public String showAdminHome() {
		return "admin-home";
	}

//	Admin add book

	@GetMapping(value = "/admin/addBook")
	public String showAddBook(Book book) {
		return "admin-add-book";
	}

	@PostMapping(value = "/admin/adding", produces = "application/json")
	public String AddBook(Book book, Model model, BindingResult bindingResult,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject(ADD_BOOK_ENDPOINT_URL, book, Book.class);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}

//	Admin delete Book

	@GetMapping(value = "/admin/deleteBook")
	public String showDeleteBook(Book book) {
		return "admin-delete-book";
	}

	@PostMapping(value = "/admin/findBook")
	public String findBook(Book book, Model model) {
		RestTemplate rt = new RestTemplate();
		Book book1 = rt.getForObject(GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-delete-book";
	}

	@GetMapping(value = "/admin/deleting")
	public String deleteBook(@RequestParam("id") String id,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.delete(DELETE_BOOK_ENDPOINT_URL + id);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}

//	Admin get Book details

	@GetMapping(value = "/admin/getBook")
	public String showGetBook(Book book) {
		return "admin-view-book";
	}

	@PostMapping(value = "/admin/findBook1")
	public String findBook1(Book book, Model model) {
		RestTemplate rt = new RestTemplate();
		Book book1 = rt.getForObject(GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-view-book-details";
	}

//	Admin update Book details

	@GetMapping(value = "/admin/updateBook")
	public String showUpdateBook(Book book) {
		return "admin-update-book";
	}

	@PostMapping(value = "/admin/findBook2")
	public String findBook2(Book book, Model model) {
		RestTemplate rt = new RestTemplate();
		Book book1 = rt.getForObject(GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-update-book";
	}

//	Admin Add User details

	@GetMapping(value = "/admin/addUser")
	public String showAddUser(User user) {
		return "admin-add-user";
	}

	@PostMapping(value = "/admin/addingUser", produces = "application/json")
	public String addUser(User user, Model model, BindingResult bindingResult,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject(CREATE_USER_ENDPOINT_URL, user, User.class);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";
		//return "redirect:http://localhost:8011/views-ws/admin/home";

	}

//	Admin delete User Details

	@GetMapping("/admin/deleteUser")
	public String showDeleteUser(User user) {
		return "admin-delete-user";
	}

	@PostMapping(value = "/admin/findUser")
	public String findUser(User user, Model model) {
		RestTemplate rt = new RestTemplate();
		User user1 = rt.getForObject(GET_USER_ENDPOINT_URL + user.getUsername(), User.class);
		String json = gson.toJson(user1);
		System.out.print(json + "hvg");
		model.addAttribute("user", user1);
		return "admin-delete-user";
	}

	@GetMapping(value = "/admin/deletingUser")
	public String deleteUser(@RequestParam("username") String username,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.delete(DELETE_USER_ENDPOINT_URL + username);
		//return "redirect:http://localhost:8011/views-ws/admin/home";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}

//	Admin View User Details

	@GetMapping(value = "/admin/getUser")
	public String showGetUser(User user) {
		return "admin-view-user";
	}

	@PostMapping(value = "/admin/findUser2")
	public String findUser1(User user, Model model) {
		System.out.println(user.getUsername());
		RestTemplate rt = new RestTemplate();
		User user1 = rt.getForObject(GET_USER_ENDPOINT_URL + user.getUsername(), User.class);
		String json = gson.toJson(user1);
		System.out.print(json + "hvg");
		model.addAttribute("user", user1);
		return "admin-view-user";
	}

//	Admin Update User Details

	@GetMapping(value = "/admin/updateUser")
	public String showUpdateBook(User user) {
		return "admin-update-user";
	}

	@PostMapping(value = "/admin/findUser3")
	public String findBook3(User user, Model model) {
		RestTemplate rt = new RestTemplate();
		User user1 = rt.getForObject(GET_USER_ENDPOINT_URL + user.getUsername(), User.class);
		String json = gson.toJson(user1);
		System.out.print(json);
		model.addAttribute("user", user1);
		return "admin-update-user";
	}

	@PostMapping(value = "/admin/updatingUser", produces = "application/json")
	public String updateUser(User user, Model model, BindingResult bindingResult,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.postForObject(UPDATE_USER_ENDPOINT_URL, user, User.class);
		//return "redirect:http://localhost:8011/views-ws/admin/home";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}
	
//	Admin View all Books
	@GetMapping(value = "/admin/showBooks")
	public String showAdminBooks(Model model) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<List<Book>> response = rt.exchange(GET_ALL_BOOKS_ENDPOINT_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});
		List<Book> books = response.getBody();
		model.addAttribute("books", books);
		return "admin-view-all-books";
	}
	
//	Admin View All Orders
	@GetMapping("/admin/showOrders")
	public String showOrders(Model model) {
		RestTemplate rt = new RestTemplate();
		List<String> list = rt.getForObject(GET_ALL_ORDER_ENDPOINT_URL, List.class);
		List<Order> order = new ArrayList<>();
		for(String l : list) {
			Order  o =new Order();
			o.setOrderid(l);
			Map<String,String> get = rt.getForObject(GET_ORDER_BOOKS_ENDPOINT_URL+l, Map.class);
			String f = "";
			int total =0;
			Set<String> set = get.keySet();
			for(String s:set) {
				Book book = rt.getForObject(GET_BOOK_ENDPOINT_URL+s, Book.class);
				f=f+book.getName()+" : "+get.get(s)+"\n";
				total = total+Integer.parseInt(book.getPrice())*Integer.parseInt(get.get(s));
			}
			o.setCount(f);
			o.setTotal(total);
			
			String userid = rt.getForObject(GET_ORDER_USER_ENDPOINT_URL+l, String.class);
			String username = rt.getForObject(GET_USERNAME_ENDPOINT_URL+userid, String.class);
			o.setUserid(username);
			order.add(o);
			
			//System.out.println(get);
		}
		model.addAttribute("order", order);
		return "admin-view-all-orders";
	}

//	User View Books
	@GetMapping(value = "/user/showBooks")
	public String showBooks(Model model, Quantity quantity, Authentication authentication) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<List<Book>> response = rt.exchange(GET_ALL_BOOKS_ENDPOINT_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});
		List<Book> books = response.getBody();
		model.addAttribute("books", books);
		String name = authentication.getName();
		UserDetail u = rt.getForObject(GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-view-all";
	}

	@GetMapping(value = "/user/ordering")
	public String orderBook(@RequestParam("id") String bookid, @RequestParam("userid") String userid,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		String c = bookid + "=1";
		rt.getForObject(ORDER_BOOK_ENDPOINT_URL + bookid, String.class);
		rt.postForObject(GENERATE_ORDER_ENDPOINT_URL + userid + "&bookid=" + c, String, String.class);
//		return "redirect:http://localhost:8011/views-ws/user/showBooks";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/showBooks";
	}

	@GetMapping(value = "/user/adding")
	public String addCart(@RequestParam("id") String userId, @RequestParam("bookid") String bookid,
			@RequestParam("count") String count, Quantity quantity,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		if(Integer.parseInt(count)<=0) {
			StringBuffer url = request.getRequestURL();
			return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/showBooks";
		}
		else {
		rt.postForObject(ADD_TO_CART_ENDPOINT_URL + userId + "&bookid=" + bookid + "&count=" + count, String,
				String.class);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/showBooks";
		}
		//return "user-view-all";

	}

//	User Cart
	@GetMapping("/user/cart")
	public String showCart(@RequestParam("userid") String id, Model model,HttpServletRequest request) {
		try {
		RestTemplate rt = new RestTemplate();
		Map<String, String> map = rt.getForObject(GET_USER_CART_ENDPOINT_URL + id, Map.class);
		Set<String> set = map.keySet();
		List<CartResponse> cartBooks = new ArrayList<>();
		int total = 0;
		for (String e : set) {
			Book book = rt.getForObject(GET_BOOK_ENDPOINT_URL + e, Book.class);
			CartResponse cart = new CartResponse();
			cart.setBookId(book.getBookId());
			cart.setAuthor(book.getAuthor());
			cart.setDescription(book.getDescription());
			cart.setName(book.getName());
			cart.setPrice(book.getPrice());
			cart.setQuantity(map.get(e));
			int subtotal = Integer.parseInt(book.getPrice()) * Integer.parseInt(map.get(e));
			cart.setSubtotal(Integer.toString(subtotal));
			cartBooks.add(cart);
		}
		for (CartResponse cart : cartBooks) {
			total = total + Integer.parseInt(cart.getSubtotal());
		}
		model.addAttribute("id", id);
		model.addAttribute("total", total);
		model.addAttribute("books", cartBooks);
		return "user-cart-view";
		}
		catch(Exception e) {
			StringBuffer url = request.getRequestURL();
			return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/emptyCart";
		}
	}

	// Order Cart
	@GetMapping("/user/ordercart")
	public String orderCart(@RequestParam("id") String userId,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.getForObject(ORDER_CART_ENDPOINT_URL + userId, String.class);
		//return "redirect:http://localhost:8011/views-ws/user/showBooks";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/showBooks";
	}

	// Delete from cart
	@GetMapping("/user/deleteFromCart")
	public String deleteFromCart(@RequestParam("id") String userId, @RequestParam("bookid") String bookid,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		rt.delete(DELETE_BOOK_FROM_CART_ENDPOINT_URL + userId + "&bookid=" + bookid);
		//return "redirect:http://localhost:8011/views-ws/user/cart?userid=" + userId;
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/cart?userid=" + userId;
	}

//	User view order
	@GetMapping("/user/orders")
	public String viewOrders(@RequestParam("id") String id, Model model) {
		RestTemplate rt = new RestTemplate();
		List<String> list = rt.getForObject(GET_USER_ORDER_ENDPOINT_URL + id, List.class);
		List<Order> order = new ArrayList<>();
		for(String l : list) {
			Order  o =new Order();
			o.setOrderid(l);
			Map<String,String> get = rt.getForObject(GET_ORDER_BOOKS_ENDPOINT_URL+l, Map.class);
			String f = "";
			int total =0;
			Set<String> set = get.keySet();
			for(String s:set) {
				Book book = rt.getForObject(GET_BOOK_ENDPOINT_URL+s, Book.class);
				f=f+book.getName()+" : "+get.get(s)+"\n";
				total = total+Integer.parseInt(book.getPrice())*Integer.parseInt(get.get(s));
			}
			o.setCount(f);
			o.setTotal(total);
			order.add(o);
			
			//System.out.println(get);
		}
		model.addAttribute("id", id);
		model.addAttribute("order", order);
		return "user-order-view";
	}

	
//	Get Empty Cart
	@GetMapping("/user/emptyCart")
	public String emptyCart(Authentication a,Model model) {
		RestTemplate rt = new RestTemplate();
		String name = a.getName();
		UserDetail u = rt.getForObject(GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-empty-cart";
	}
	
//	Get Index Page
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
//	Get User Home page
	@GetMapping("/user/home")
	public String userHome(Authentication a,Model model) {
		RestTemplate rt = new RestTemplate();
		String name = a.getName();
		UserDetail u = rt.getForObject(GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-home";
	}
}
