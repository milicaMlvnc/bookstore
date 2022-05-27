package com.bookstore.dto;

import java.io.Serializable;

import com.bookstore.entity.Author;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthorDB implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
	private String lastName;
	
	public AuthorDB(Author ent) {
		this.id = ent.getId();
		this.firstName = ent.getFirstName();
		this.lastName = ent.getLastName();
	}
	
}
