package com.fsd.springmvc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.fsd.springmvc.model.Book;
import com.fsd.springmvc.model.Subject;

public class SubjectExtractor implements ResultSetExtractor<List<Subject>> {

	@Override
	public List<Subject> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		List<Subject> subjectsList = new ArrayList<Subject>();
		
		long currentSubjectId = 0;
		long previousSubjectId = 0;
		
		Set<Book> books = null;
		Subject subject = null;
		
		while(rs.next()) {

			currentSubjectId = rs.getLong("SUBJECT_ID");
			
			if(currentSubjectId != previousSubjectId) {
				
				subject = new Subject();
				books = new HashSet<Book>();
				
				subject.setSubjectId(currentSubjectId);
				subject.setSubjectTitle(rs.getString("SUBJECT_TITLE"));
				subject.setDurationHours(rs.getInt("DURATION_IN_HOURS"));
				
				books.add(createBook(rs));
				
				subject.setBooks(books);
				subjectsList.add(subject);
				
			} else {
				
				books.add(createBook(rs));
			}
			
			previousSubjectId = currentSubjectId;
		}
		
		return subjectsList;
		
	}
	
	
	private Book createBook(ResultSet rs) throws SQLException {
		
		Book book = new Book();
		
		book.setBookId(rs.getLong("BOOK_ID"));
		book.setTitle(rs.getString("TITLE"));
		book.setPrice(rs.getDouble("PRICE"));
		book.setVolume(rs.getInt("VOLUME"));
		book.setPublishDate(rs.getDate("PUBLISH_DATE"));
		
		return book;
		
		
	}

	
	
}
