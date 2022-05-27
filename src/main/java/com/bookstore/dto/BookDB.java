package com.bookstore.dto;

import java.io.Serializable;

import com.bookstore.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BookDB implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String description;
	private int pageNumber;
	private int price;

	private AuthorDB fkAuthor;
	
	public BookDB(Book ent) {
		this(ent, false);
	}
	
	public BookDB(Book ent, Boolean includeFk) {
		this.id = ent.getId();
		this.title = ent.getTitle();
		this.description = ent.getDescription();
		this.pageNumber = ent.getPageNumber();
		this.price = ent.getPrice();
		
		if (includeFk) {
			this.fkAuthor = new AuthorDB(ent.getAuthor());
		}
	}
	
}
