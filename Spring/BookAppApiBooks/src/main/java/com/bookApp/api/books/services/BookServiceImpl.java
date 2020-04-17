package com.bookApp.api.books.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookApp.api.books.model.Books;
import com.bookApp.api.books.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{
	
private BookRepository repo;
	
	public BookServiceImpl(BookRepository repo) {
		this.repo = repo;
	}

	@Override
	public void save(Books book) {
		// TODO Auto-generated method stub
		repo.save(book);
		
	}

	@Override
	public void deleteById(String bookId) {
		// TODO Auto-generated method stub
		repo.deleteById(bookId);
		
	}

	@Override
	public Optional<Books> findById(String bookId) {
		// TODO Auto-generated method stub
		return repo.findById(bookId);
	}

	@Override
	public List<Books> findAll() {
		// TODO Auto-generated method stub
		return  (List<Books>) repo.findAll();
	}


}
