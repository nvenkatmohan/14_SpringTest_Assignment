package com.fsd.springmvc.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fsd.springmvc.dao.BookDao;
import com.fsd.springmvc.model.Book;
import com.fsd.springmvc.util.BookException;
import com.fsd.springmvc.util.SerializationUtil;

@Service
public class BookService {
		
	@Autowired
	protected BookDao bookDao;
	
	public List<Book> retriveAllBooks() throws BookException {
		
		List<Book> booksList = this.bookDao.retrieveAllBooks(); 
		
		return booksList;
	}
	
	public Book searchBookById(long bookId) {
		
		Book book = this.bookDao.searchBookById(bookId);
		
		return book;
	}
	
	public Book searchBookByTitle(String title) {
		
		Book book = this.bookDao.searchBookByTitle(title);
		
		return book;
	}
	
	public void addBook(Book book) {
		
		this.bookDao.addBook(book);
	}
	
	public void deleteBookById(long bookId) {
		
		this.bookDao.deleteBookById(bookId);
		
	}

}
