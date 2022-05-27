package com.bookstore.graphql.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class MutationResolver implements GraphQLMutationResolver {
	
	@Autowired private BookRepository bookRepo;
	
	public Book save(Integer id, String title, String description, Integer pageNumber, Integer price, Integer authorId) {
        Book book = new Book();
		
        book.setTitle(title);
        book.setDescription(description);
        book.setPageNumber(pageNumber);
        book.setPrice(price);
        
        Author author = new Author();
        author.setId(authorId);
        book.setAuthor(author);
        
        book = bookRepo.saveAndFlush(book);

        return book;
	}
	
	public List<Book>  saveList(List<Book> books) {
		bookRepo.saveAll(books);
		return books;
	}
	
}
