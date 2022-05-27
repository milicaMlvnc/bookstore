package com.bookstore.grpc.services;

import grpcBookstore.BookList;
import grpcBookstore.BookProto;
import grpcBookstore.BookServiceGrpc.BookServiceImplBase;
import grpcBookstore.NoParamRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookServiceImpl extends BookServiceImplBase {
	
	@Override
	public void getAll(NoParamRequest request, StreamObserver<BookList> res) {
		
	}
	
	public void savePost(BookProto request, StreamObserver<BookProto> res) {
		
	}

	public void saveList(BookList request, StreamObserver<NoParamRequest> res) {
		
	}
	
}
