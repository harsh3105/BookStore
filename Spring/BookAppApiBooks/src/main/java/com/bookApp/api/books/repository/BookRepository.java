package com.bookApp.api.books.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.bookApp.api.books.model.Book;

public interface BookRepository extends CassandraRepository<Book,String>{

}
