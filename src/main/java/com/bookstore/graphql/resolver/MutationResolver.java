package com.bookstore.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class MutationResolver implements GraphQLMutationResolver {
	
	@Autowired private BookRepository bookRepo;
	
	public Book save(Integer id, String title, String description, String pageNumber, String price, Integer authorId) {
        Book book = new Book();
		
        book.setTitle(title);
        book.setDescription(description);
        book.setPageNumber(Integer.parseInt(pageNumber));
        book.setPrice(Integer.parseInt(price));
        
        Author author = new Author();
        author.setId(authorId);
        book.setAuthor(author);
        
        book = bookRepo.saveAndFlush(book);

        return book;
	}
	
//	description: String
//	  pageNumber: String
	
//	saveList([Book!]!): Void
//	public void saveList(List<Book> books) {
//		List<Book> booksToSave = new ArrayList<>();
//		for (Book bookDB : booksDB) {
//			Book book = new Book();
//			
//	        book.setDescription(bookDB.getDescription());
//	        book.setTitle(bookDB.getTitle());
//	        book.setPageNumber(bookDB.getPageNumber());
//	        book.setPrice(bookDB.getPrice());
//	        
//	        Author author = new Author();
//	        author.setId(bookDB.getFkAuthor().getId());
//	        book.setAuthor(author);
//	        
//	        booksToSave.add(book);
//		}
//
//		bookRepo.saveAll(booksToSave);
//	}

}
