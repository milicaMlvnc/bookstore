package com.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bookstore.dto.BookDB;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

@Controller
public class BookController {

	@Autowired private BookRepository bookRepo;
	
	public BookDB save(BookDB bookDB) {
		Book book = new Book();
		
        book.setDescription(bookDB.getDescription());
        book.setTitle(bookDB.getTitle());
        book.setPageNumber(bookDB.getPageNumber());
        book.setPrice(bookDB.getPrice());
        
        Author author = new Author();
        author.setId(bookDB.getFkAuthor().getId());
        book.setAuthor(author);
        
        book = bookRepo.saveAndFlush(book);
        bookDB.setId(book.getId());

        return bookDB;
	}
	
	public List<BookDB> getAll() {
		List<Book> books = bookRepo.findAll();
		List<BookDB> resultDB = new ArrayList<>();
		
		for (Book book : books) {
			BookDB bookDB = new BookDB(book);
			resultDB.add(bookDB);
		}
		
		return resultDB;
	}
	
	public List<BookDB> saveList(List<BookDB> booksDB) {
		List<Book> booksToSave = new ArrayList<>();
		for (BookDB bookDB : booksDB) {
			Book book = new Book();
			
	        book.setDescription(bookDB.getDescription());
	        book.setTitle(bookDB.getTitle());
	        book.setPageNumber(bookDB.getPageNumber());
	        book.setPrice(bookDB.getPrice());
	        
	        Author author = new Author();
	        author.setId(bookDB.getFkAuthor().getId());
	        book.setAuthor(author);
	        
	        booksToSave.add(book);
		}

		booksToSave = bookRepo.saveAll(booksToSave);
		
		List<BookDB> resultDB = new ArrayList<>();
		for (Book book : booksToSave) {
			BookDB bookDB = new BookDB(book, true);
			resultDB.add(bookDB);
		}
		
		return resultDB;
	}
	
}
