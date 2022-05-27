package com.bookstore.grpc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;

import grpcBookstore.AuthorProto;
import grpcBookstore.BookList;
import grpcBookstore.BookProto;
import grpcBookstore.BookServiceGrpc.BookServiceImplBase;
import grpcBookstore.NoParamRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookServiceImpl extends BookServiceImplBase {
	
	@Autowired private BookRepository bookRepo;
	
	@Override
	public void getAll(NoParamRequest request, StreamObserver<BookList> res) {
		List<Book> books = bookRepo.findAll();
		List<BookProto> bookResponse = new ArrayList<>();
		
		for (Book book : books) {
			AuthorProto authorProto = AuthorProto.newBuilder()
					.setId(book.getId())
					.setFirstName(book.getAuthor().getFirstName())
					.setLastName(book.getAuthor().getLastName())
					.build();
			BookProto postProto = BookProto.newBuilder().setId(book.getId())
					.setDescription(book.getDescription())
					.setTitle(book.getTitle())
					.setPageNumber(book.getPageNumber())
					.setPrice(book.getPrice())
					.setAuthor(authorProto)
					.build();
			bookResponse.add(postProto);
		}
		BookList response = BookList.newBuilder().addAllBooks(bookResponse).build();
		
		res.onNext(response);
		res.onCompleted();
	}
	
	public void savePost(BookProto book, StreamObserver<BookProto> res) {
		Book bookToSave = new Book();
		
        bookToSave.setDescription(book.getDescription());
        bookToSave.setTitle(book.getTitle());
        bookToSave.setPageNumber(book.getPageNumber());
        bookToSave.setPrice(book.getPrice());
        bookToSave.setAuthor(new Author(book.getAuthor().getId()));
        
        bookToSave = bookRepo.saveAndFlush(bookToSave);
		
        AuthorProto authorProto = AuthorProto.newBuilder()
				.setId(book.getId())
				.setFirstName(book.getAuthor().getFirstName())
				.setLastName(book.getAuthor().getLastName())
				.build();
        
		BookProto response = BookProto.newBuilder().setId(bookToSave.getId())
				.setDescription(book.getDescription())
				.setTitle(book.getTitle())
				.setPageNumber(book.getPageNumber())
				.setPrice(book.getPrice())
				.setAuthor(authorProto)
				.build();
		
		res.onNext(response);
		res.onCompleted();
	}

	public void saveList(BookList request, StreamObserver<BookList> res) {
		List<BookProto> bookResponse = new ArrayList<>();
		
		List<Book> booksToSave = new ArrayList<>();
		
		for (BookProto bookProto : request.getBooksList()) {
			Book bookToSave = new Book();
			bookToSave.setId(bookProto.getId());
			bookToSave.setTitle(bookProto.getTitle());
			bookToSave.setDescription(bookProto.getDescription());
			bookToSave.setPageNumber(bookProto.getPageNumber());
			bookToSave.setPrice(bookProto.getPrice());
			bookToSave.setAuthor(new Author(bookProto.getAuthor().getId()));
			
			booksToSave.add(bookToSave);
		}
		
		booksToSave = bookRepo.saveAll(booksToSave);
		
		for (Book book : booksToSave) {
			AuthorProto authorProto = AuthorProto.newBuilder()
					.setId(book.getId())
					.setFirstName(book.getAuthor().getFirstName())
					.setLastName(book.getAuthor().getLastName())
					.build();
	        
			BookProto bookProto = BookProto.newBuilder().setId(book.getId())
					.setDescription(book.getDescription())
					.setTitle(book.getTitle())
					.setPageNumber(book.getPageNumber())
					.setPrice(book.getPrice())
					.setAuthor(authorProto)
					.build();
			
			bookResponse.add(bookProto);
		}
		BookList response = BookList.newBuilder().addAllBooks(bookResponse).build();

		res.onNext(response);
		res.onCompleted();
	}
	
}
