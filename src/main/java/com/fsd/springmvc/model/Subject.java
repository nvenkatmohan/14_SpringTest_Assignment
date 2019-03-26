package com.fsd.springmvc.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Subject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6470090955514209556L;

	@NotNull
	@Digits(integer=10, fraction=0)
	private Long subjectId;
	
	@NotEmpty
	private String subjectTitle;
	
	@Digits(integer=4, fraction=0)
	private Integer durationHours;
	
	@NotEmpty
	private String bookTitles;
	
	private Set<Book> books;
	
	public Subject(){}
	
	public Subject (Long subjectId, String subjectTitle, Integer durationHours,
			Set<Book> referencedBooks) {
		
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
		this.durationHours = durationHours;
		this.books = referencedBooks;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}	
	
	public String getBookTitles() {
		
		if(this.bookTitles == null && this.books != null && 
				!this.books.isEmpty()) {
			
			StringBuilder bookTitle = new StringBuilder();
			
			for(Book book : books)
				bookTitle.append(book.getTitle()).append(",");
			
			this.bookTitles = bookTitle.substring(0, (bookTitle.length() - 1));
		}
		
		
		return bookTitles;
	}

	public void setBookTitles(String bookTitles) {
		this.bookTitles = bookTitles;
	}

	@Override
	public String toString() {

		StringBuilder refrencedBooks = new StringBuilder();
		
		if(this.books != null && !this.books.isEmpty()) {
			
			for(Book book : this.books) {
				refrencedBooks.append(book.toString()+",");
			}
		}
		
		// TODO Auto-generated method stub
		return "Subject[Subject Id: "+this.subjectId+", Subject Title: "+this.subjectTitle
				+", Duration Hours: "+this.durationHours+", Books: {{"+refrencedBooks.toString()
				+"}}]";
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        
        if (!(o instanceof Subject)) {
            return false;
        }
        
        Subject subject = (Subject) o;
        return  Objects.equals(this.subjectTitle, subject.subjectTitle);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(this.subjectTitle);
	}

}
