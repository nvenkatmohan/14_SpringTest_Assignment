package com.fsd.springmvc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6470090944414208496L;

	@NotNull
	@Digits(integer=10, fraction=0)
	private Long bookId;
	
	@NotEmpty
	private String title;
	
	@Digits(integer=10, fraction=2)
	private Double price;
	
	@Digits(integer=10, fraction=0)
	private Integer volume;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date publishDate;
	
	public Book() {}
	
	public Book(Long bookId, String title, Integer volume,
			Double price, Date publishDate) {
		
		this.bookId = bookId;
		this.title = title;
		this.volume = volume;
		this.publishDate = publishDate;
		this.price = price;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Book[Book Id: "+this.bookId+", Title: "+this.title
				+", Volume: "+this.volume+", Publish Date: "+this.publishDate
				+"]";
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        
        if (!(o instanceof Book)) {
            return false;
        }
        
        Book book = (Book) o;
        return  Objects.equals(title, book.title);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}
	
	
	
	
}
