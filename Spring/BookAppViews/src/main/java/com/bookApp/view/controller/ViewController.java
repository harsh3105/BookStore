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
import com.bookApp.view.model.EachOrder;
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

//	// admin
//	private static final String CREATE_USER_ENDPOINT_URL = "http://users:2222/users/addUser";
//	private static final String ADD_BOOK_ENDPOINT_URL = "http://books:2222/books/addBook";
//	private static final String GET_BOOK_ENDPOINT_URL = "http://books:2222/books/getBook?id=";
//	private static final String DELETE_BOOK_ENDPOINT_URL = "http://books:2222/books/deleteBook?id=";
//	private static final String GET_USER_ENDPOINT_URL = "http://users:2222/users/getUser?username=";
//	private static final String DELETE_USER_ENDPOINT_URL = "http://users:2222/users/deleteUser?username=";
//	private static final String UPDATE_USER_ENDPOINT_URL = "http://users:2222/users/updateUser";
//	private static final String GET_USERNAME_ENDPOINT_URL = "http://users:2222/users/getUsername?id=";
//
//	// user
//	private static final String GET_ALL_BOOKS_ENDPOINT_URL = "http://books:2222/books/getAllBook";
//	private static final String ORDER_BOOK_ENDPOINT_URL = "http://books:2222/books/orderBook?id=";
//	private static final String GENERATE_ORDER_ENDPOINT_URL = "http://order:2222/orders/addOrder?userid=";
//	private static final String ADD_TO_CART_ENDPOINT_URL = "http://order:2222//cart/add?id=";
//	private static final String GET_USER_CART_ENDPOINT_URL = "http://order:2222/cart/getCart?id=";
//	private static final String ORDER_CART_ENDPOINT_URL = "http://order:2222/cart/orderCart?id=";
//	private static final String DELETE_BOOK_FROM_CART_ENDPOINT_URL = "http://order:2222/cart/deletefromcart?id=";
//	private static final String GET_USER_ORDER_ENDPOINT_URL = "http://order:2222/orders/getOrder?id=";
//	private static final String GET_ORDER_BOOKS_ENDPOINT_URL = "http://order:2222/orders/getBooks?orderid=";
//	private static final String GET_ALL_ORDER_ENDPOINT_URL = "http://order:2222/orders/getAllOrders";
//	private static final String GET_ORDER_USER_ENDPOINT_URL = "http://order:2222/orders/getOrderUser?id=";
//	private static final Object String = null;
	
	// admin
		private static final String CREATE_USER_ENDPOINT_URL = ":7500/users/addUser";
		private static final String ADD_BOOK_ENDPOINT_URL = ":7501/books/addBook";
		private static final String GET_BOOK_ENDPOINT_URL = ":7501/books/getBook?id=";
		private static final String DELETE_BOOK_ENDPOINT_URL = ":7501/books/deleteBook?id=";
		private static final String GET_USER_ENDPOINT_URL = ":7500/users/getUser?username=";
		private static final String DELETE_USER_ENDPOINT_URL = ":7500/users/deleteUser?username=";
		private static final String UPDATE_USER_ENDPOINT_URL = ":7500/users/updateUser";
		private static final String GET_USERNAME_ENDPOINT_URL = ":7500/users/getUsername?id=";

		// user
		private static final String GET_ALL_BOOKS_ENDPOINT_URL = ":7501/books/getAllBook";
		private static final String ORDER_BOOK_ENDPOINT_URL = ":7501/books/orderBook?id=";
		private static final String GENERATE_ORDER_ENDPOINT_URL = ":7502/orders/addOrder?userid=";
		private static final String ADD_TO_CART_ENDPOINT_URL = ":7502/cart/add?id=";
		private static final String GET_USER_CART_ENDPOINT_URL = ":7502/cart/getCart?id=";
		private static final String ORDER_CART_ENDPOINT_URL = ":7502/cart/orderCart?id=";
		private static final String DELETE_BOOK_FROM_CART_ENDPOINT_URL = ":7502/cart/deletefromcart?id=";
		private static final String GET_USER_ORDER_ENDPOINT_URL = ":7502/orders/getOrder?id=";
		private static final String GET_ORDER_BOOKS_ENDPOINT_URL = ":7502/orders/getBooks?orderid=";
		private static final String GET_ALL_ORDER_ENDPOINT_URL = ":7502/orders/getAllOrders";
		private static final String GET_ORDER_USER_ENDPOINT_URL = ":7502/orders/getOrderUser?id=";
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
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		User e = rt.postForObject(host+CREATE_USER_ENDPOINT_URL, user, User.class);
		StringBuffer url = request.getRequestURL();
		System.out.println(url.subSequence(0, url.lastIndexOf("/")));
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/login";
	}

//	Login page
	@GetMapping("/login")
	public String ShowLogin(@RequestParam(value = "error", required = false) String error) {
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
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.postForObject(host+ADD_BOOK_ENDPOINT_URL, book, Book.class);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}

//	Admin delete Book

	@GetMapping(value = "/admin/deleteBook")
	public String showDeleteBook(Book book) {
		return "admin-delete-book";
	}

	@PostMapping(value = "/admin/findBook")
	public String findBook(Book book, Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		Book book1 = rt.getForObject(host+GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		if(book1==null) {
			return "admin-delete-book";
		}
		else {
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-delete-book";
		}
	}

	@GetMapping(value = "/admin/deleting")
	public String deleteBook(@RequestParam("id") String id,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.delete(host+DELETE_BOOK_ENDPOINT_URL + id);
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}

//	Admin get Book details

	@GetMapping(value = "/admin/getBook")
	public String showGetBook(Book book) {
		return "admin-view-book";
	}

	@PostMapping(value = "/admin/findBook1")
	public String findBook1(Book book, Model model,HttpServletRequest request) {

		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		Book book1 = rt.getForObject(host+GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		if(book1==null) {
			return "admin-view-book";
		}
		else {
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-view-book-details";
		}
	}

//	Admin update Book details

	@GetMapping(value = "/admin/updateBook")
	public String showUpdateBook(Book book) {
		return "admin-update-book";
	}

	@PostMapping(value = "/admin/findBook2")
	public String findBook2(Book book, Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		Book book1 = rt.getForObject(host+GET_BOOK_ENDPOINT_URL + book.getBookId(), Book.class);
		if(book1==null) {
			return "admin-update-book";
		}
		else {
		String json = gson.toJson(book1);
		System.out.print(json);
		model.addAttribute("book", book1);
		return "admin-update-book";
		}
		
	}

//	Admin Add User details

	@GetMapping(value = "/admin/addUser")
	public String showAddUser(User user) {
		return "admin-add-user";
	}

	@PostMapping(value = "/admin/addingUser", produces = "application/json")
	public String addUser(User user, Model model, BindingResult bindingResult,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.postForObject(host+CREATE_USER_ENDPOINT_URL, user, User.class);
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
	public String findUser(User user, Model model,HttpServletRequest request) {
		try {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		User user1 = rt.getForObject(host+GET_USER_ENDPOINT_URL + user.getUsername(), User.class);
		String json = gson.toJson(user1);
		System.out.print(json + "hvg");
		model.addAttribute("user", user1);
		return "admin-delete-user";
		}
		catch(Exception e) {
			return "admin-delete-user";
		}
	}

	@GetMapping(value = "/admin/deletingUser")
	public String deleteUser(@RequestParam("username") String username,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.delete(host+DELETE_USER_ENDPOINT_URL + username);
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
	public String findUser1(User user, Model model,HttpServletRequest request) {
		try {
		System.out.println(user.getUsername());
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		User user1 = rt.getForObject(host+GET_USER_ENDPOINT_URL + user.getUsername(), User.class);
		String json = gson.toJson(user1);
		System.out.print(json + "hvg");
		model.addAttribute("user", user1);
		return "admin-view-user";
		}
		catch(Exception e) {
			return "admin-view-user";
		}
	}

//	Admin Update User Details

	@GetMapping(value = "/admin/updateUser")
	public String showUpdateBook(User user) {
		return "admin-update-user";
	}

	@PostMapping(value = "/admin/findUser3")
	public String findBook3(User user, Model model,HttpServletRequest request) {
		try {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		User user1 = rt.getForObject(host+GET_USER_ENDPOINT_URL + user.getUsername(), User.class);
		String json = gson.toJson(user1);
		System.out.print(json);
		model.addAttribute("user", user1);
		return "admin-update-user";
		}
		catch(Exception e) {
			return "admin-update-user";
		}
	}

	@PostMapping(value = "/admin/updatingUser", produces = "application/json")
	public String updateUser(User user, Model model, BindingResult bindingResult,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.postForObject(host+UPDATE_USER_ENDPOINT_URL, user, User.class);
		//return "redirect:http://localhost:8011/views-ws/admin/home";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/home";

	}
	
//	Admin View all Books
	@GetMapping(value = "/admin/showBooks")
	public String showAdminBooks(Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		ResponseEntity<List<Book>> response = rt.exchange(host+GET_ALL_BOOKS_ENDPOINT_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});
		List<Book> books = response.getBody();
		model.addAttribute("books", books);
		return "admin-view-all-books";
	}
	
//	Admin View All Orders
	@GetMapping("/admin/showOrders")
	public String showOrders(Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		List<String> list = rt.getForObject(host+GET_ALL_ORDER_ENDPOINT_URL, List.class);
		List<Order> order = new ArrayList<>();
		String OID = "OID000";
		Integer i =0;
		for(String l : list) {
			Order  o =new Order();
			++i;
			o.setOrderid(OID+i.toString());
			Map<String,String> get = rt.getForObject(host+GET_ORDER_BOOKS_ENDPOINT_URL+l, Map.class);
			String f = "";
			int total =0;
			Set<String> set = get.keySet();
			for(String s:set) {
				Book book = rt.getForObject(host+GET_BOOK_ENDPOINT_URL+s, Book.class);
				f=f+book.getName()+" : "+get.get(s)+"\n";
				total = total+Integer.parseInt(book.getPrice())*Integer.parseInt(get.get(s));
			}
			o.setCount(f);
			o.setTotal(total);
			
			String userid = rt.getForObject(host+GET_ORDER_USER_ENDPOINT_URL+l, String.class);
			String username = rt.getForObject(host+GET_USERNAME_ENDPOINT_URL+userid, String.class);
			o.setUserid(username);
			order.add(o);
			
			//System.out.println(get);
		}
		model.addAttribute("order", order);
		return "admin-view-all-orders";
	}

//	User View Books
	@GetMapping(value = "/user/showBooks")
	public String showBooks(Model model, Quantity quantity, Authentication authentication,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		ResponseEntity<List<Book>> response = rt.exchange(host+GET_ALL_BOOKS_ENDPOINT_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});
		List<Book> books = response.getBody();
		model.addAttribute("books", books);
		String name = authentication.getName();
		UserDetail u = rt.getForObject(host+GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-view-all";
	}

	@GetMapping(value = "/user/ordering")
	public String orderBook(@RequestParam("id") String bookid, @RequestParam("userid") String userid,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		String c = bookid + "=1";
		rt.getForObject(host+ORDER_BOOK_ENDPOINT_URL + bookid, String.class);
		rt.postForObject(host+GENERATE_ORDER_ENDPOINT_URL + userid + "&bookid=" + c, String, String.class);
//		return "redirect:http://localhost:8011/views-ws/user/showBooks";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/showBooks";
	}

	@GetMapping(value = "/user/adding")
	public String addCart(@RequestParam("id") String userId, @RequestParam("bookid") String bookid,
			@RequestParam("count") String count, Quantity quantity,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		if(Integer.parseInt(count)<=0) {
			StringBuffer url = request.getRequestURL();
			return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/showBooks";
		}
		else {
		rt.postForObject(host+ADD_TO_CART_ENDPOINT_URL + userId + "&bookid=" + bookid + "&count=" + count, String,
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
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		Map<String, String> map = rt.getForObject(host+GET_USER_CART_ENDPOINT_URL + id, Map.class);
		Set<String> set = map.keySet();
		List<CartResponse> cartBooks = new ArrayList<>();
		int total = 0;
		for (String e : set) {
			Book book = rt.getForObject(host+GET_BOOK_ENDPOINT_URL + e, Book.class);
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
	
//	view Order
	@GetMapping("/user/eachOrder")
	public String showEachOrder(@RequestParam("orderid") String orderid, Model model,HttpServletRequest request,Authentication a) {
		RestTemplate rt = new RestTemplate();
		StringBuffer b = request.getRequestURL();
		String host = (String) b.subSequence(0, b.lastIndexOf(":"));
		String name = a.getName();
		UserDetail u = rt.getForObject(host+GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		
		Map<String,String> get = rt.getForObject(host+GET_ORDER_BOOKS_ENDPOINT_URL+orderid, Map.class);
		int total =0;
		ArrayList<EachOrder> orderdetails = new ArrayList<>();
		Set<String> set = get.keySet();
		for(String s:set) {
			Book book = rt.getForObject(host+GET_BOOK_ENDPOINT_URL+s, Book.class);
			EachOrder order = new EachOrder();
			order.setName(book.getName());
			order.setPrice(book.getPrice());
			//order.setQuantity(Integer.toString(book.getQuantity()));
			order.setDescription(book.getDescription());
			order.setAuthor(book.getAuthor());
			order.setQuantity(get.get(s));
			int sub = Integer.parseInt(book.getPrice())*Integer.parseInt(get.get(s));
			total = total+sub;
			order.setSubtotal(Integer.toString(sub));
			orderdetails.add(order);
		}
		model.addAttribute("total", total);
		model .addAttribute("books", orderdetails);
		
		return "user-view-each-order";
	}

	// Order Cart
	@GetMapping("/user/ordercart")
	public String orderCart(@RequestParam("id") String userId,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.getForObject(host+ORDER_CART_ENDPOINT_URL + userId, String.class);
		//return "redirect:http://localhost:8011/views-ws/user/showBooks";
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/orderedCart";
	}

	// Delete from cart
	@GetMapping("/user/deleteFromCart")
	public String deleteFromCart(@RequestParam("id") String userId, @RequestParam("bookid") String bookid,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		rt.delete(host+DELETE_BOOK_FROM_CART_ENDPOINT_URL + userId + "&bookid=" + bookid);
		//return "redirect:http://localhost:8011/views-ws/user/cart?userid=" + userId;
		StringBuffer url = request.getRequestURL();
		return "redirect:"+url.subSequence(0, url.lastIndexOf("/"))+"/cart?userid=" + userId;
	}

//	User view order
	@GetMapping("/user/orders")
	public String viewOrders(@RequestParam("id") String id, Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer a = request.getRequestURL();
		String host = (String) a.subSequence(0, a.lastIndexOf(":"));
		List<String> list = rt.getForObject(host+GET_USER_ORDER_ENDPOINT_URL + id, List.class);
		List<Order> order = new ArrayList<>();
		String oid = "OID000";
		Integer b =0;
		for(String l : list) {
			Order  o =new Order();
			++b;
			o.setOrderid(oid+b.toString());
			Map<String,String> get = rt.getForObject(host+GET_ORDER_BOOKS_ENDPOINT_URL+l, Map.class);
			String f = "";
			int total =0;
			Set<String> set = get.keySet();
			for(String s:set) {
				Book book = rt.getForObject(host+GET_BOOK_ENDPOINT_URL+s, Book.class);
				f=f+book.getName()+" : "+get.get(s)+", ";
				total = total+Integer.parseInt(book.getPrice())*Integer.parseInt(get.get(s));
			}
			f=f.substring(0, f.lastIndexOf(','));
			o.setCount(f);
			o.setTotal(total);
			o.setId(l);
			order.add(o);
			
			//System.out.println(get);
		}
		model.addAttribute("id", id);
		model.addAttribute("order", order);
		return "user-order-view";
	}

	
//	Get Empty Cart
	@GetMapping("/user/emptyCart")
	public String emptyCart(Authentication a,Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer b = request.getRequestURL();
		String host = (String) b.subSequence(0, b.lastIndexOf(":"));
		String name = a.getName();
		UserDetail u = rt.getForObject(host+GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-empty-cart";
	}
	
//	Get Successful Order cart
	@GetMapping("/user/orderedCart")
	public String orderCart(Authentication a,Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer b = request.getRequestURL();
		String host = (String) b.subSequence(0, b.lastIndexOf(":"));
		String name = a.getName();
		UserDetail u = rt.getForObject(host+GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-order-done";
	}
	
//	Get Index Page
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
//	Get User Home page
	@GetMapping("/user/home")
	public String userHome(Authentication a,Model model,HttpServletRequest request) {
		RestTemplate rt = new RestTemplate();
		StringBuffer b = request.getRequestURL();
		String host = (String) b.subSequence(0, b.lastIndexOf(":"));
		String name = a.getName();
		UserDetail u = rt.getForObject(host+GET_USER_ENDPOINT_URL+name, UserDetail.class);
		model.addAttribute("id", u.getUserid());
		return "user-home";
	}
	
}
