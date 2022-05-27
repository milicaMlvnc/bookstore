package com.bookstore.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component 
public class QueryResolver implements GraphQLQueryResolver {
 
	@Autowired private BookRepository bookRepo;
	
	public List<Book> getAll() {
		List<Book> books = bookRepo.findAll();;
		return books;
	}
	
}
