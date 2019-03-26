package com.fsd.springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fsd.springmvc.model.Book;
import com.fsd.springmvc.util.QueryConstants;

@Repository
@Qualifier("bookDao")
public class BookDaoImpl implements BookDao {

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	@Override
	public void addBook(Book book) {
	
		this.jdbcTemplate.update(
                QueryConstants.INSERT_BOOK_SQL,
                new Object[] {book.getBookId(),book.getTitle(),
                		book.getPrice(),book.getVolume(),book.getPublishDate()});
		
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Book searchBookById(long bookId) {

		Book book = null;
		
		try {
		
			book = (Book) this.jdbcTemplate.queryForObject(
                QueryConstants.SELECT_BOOK_BY_BOOK_ID_SQL,
                new Object[] { bookId },
                new BeanPropertyRowMapper(Book.class));		
		
		} catch (EmptyResultDataAccessException e) {
			
		}	
		
		return book;
		
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Book searchBookByTitle(String title) {

		Book book = null;
		
		try {
		
			book = (Book) this.jdbcTemplate.queryForObject(
	                QueryConstants.SELECT_BOOK_BY_TITLE_SQL,
	                new Object[] { title },
	                new BeanPropertyRowMapper(Book.class));			
		
		} catch (EmptyResultDataAccessException e) {
			
		}
		
		return book;
		
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Book> retrieveAllBooks() {
		
		 List<Book> booksList = null;
		 
		 try {
		
			 booksList = this.jdbcTemplate.query(
                QueryConstants.SELECT_ALL_BOOKS_SQL,
                new BeanPropertyRowMapper(Book.class));
		 
		 } catch(EmptyResultDataAccessException erdaExce) {
			 
		 }
		
		return booksList;
	}

	@Override
	public void deleteBookById(long bookId) {
		
		this.jdbcTemplate.update(QueryConstants.DELETE_BOOK_SQL,
				new Object[]{bookId});
	}

}
