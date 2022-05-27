package com.bookstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.controller.BookController;
import com.bookstore.dto.BookDB;

@RestController
@RequestMapping("/book")
public class BookRest {

	@Autowired private BookController bookController;
	
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	public BookDB save(@RequestBody BookDB bookDB) {
		return bookController.save(bookDB);
	}
	
	@GetMapping(value = "/getAll", produces = "application/json")
    public List<BookDB> getAll() {
        return bookController.getAll();
    }
	
//	@PostMapping(value = "/saveList", consumes = "application/json", produces = "application/json")
//	public void saveList(@RequestBody List<BookDB> booksDB) {
//		bookController.saveList(booksDB);
//	}
	
}
