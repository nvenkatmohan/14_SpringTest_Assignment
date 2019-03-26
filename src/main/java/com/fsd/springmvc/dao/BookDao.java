package com.fsd.springmvc.dao;

import java.util.List;

import com.fsd.springmvc.model.Book;

public interface BookDao {

	public void addBook(Book book);
	
	public Book searchBookById(long bookId);
	
	public Book searchBookByTitle(String title);
	
	public List<Book> retrieveAllBooks();
	
	public void deleteBookById(long bookId);
	
}
