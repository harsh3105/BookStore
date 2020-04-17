package com.bookApp.api.books.repository;


import org.springframework.data.repository.CrudRepository;

import com.bookApp.api.books.model.Books;

public interface BookRepository extends CrudRepository<Books, String>{

}
