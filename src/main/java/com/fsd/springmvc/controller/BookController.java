package com.fsd.springmvc.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsd.springmvc.model.Book;
import com.fsd.springmvc.model.SearchCriteria;
import com.fsd.springmvc.service.BookService;
import com.fsd.springmvc.util.BookException;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	private Set<Book> bookSet;
	
	@RequestMapping("/addBook")
	public String addABook(Model model) {
		
		Book newBook = new Book();
		model.addAttribute("book", newBook);
		
		return "addbook";
	}
	
	@RequestMapping("/savebook")
	public String saveBook(@Valid Book newBook, BindingResult result, ModelMap model) {
		
		model.addAttribute("book", newBook);
		
		if(!result.hasErrors()) {
			
			System.out.println("New Book is "+newBook.toString());
			
			List<String> messages = new ArrayList<String>();
			
			try {
				
				Book existingBook = this.bookService.searchBookById(newBook.getBookId());
				
				if(existingBook != null) {
					model.addAttribute("infoMsg", "Book already exisits with Book Id: "+newBook.getBookId()+". Please save book with other Id");
					return "addbook";
				}
				
				existingBook =  this.bookService.searchBookByTitle(newBook.getTitle().trim());
				
				if(existingBook != null) {
					model.addAttribute("infoMsg", "Book already exisits with Title: "+newBook.getTitle().trim()+". Please save book with other Title");
					return "addbook";
				}
				
				if(newBook != null) {
					
					this.bookService.addBook(newBook);
					
					model.addAttribute("successMsg", "Book has been added succcessfully");
				}
				
			} catch (Exception exce) {
				
				exce.printStackTrace();
				messages.add("Error occured while saving Book");
				model.addAttribute("messages", messages);
			}
		}
		 		
		return "addbook";
	}
	
	@RequestMapping("/searchbook")
	public String searchABook(Model model) {
		
		SearchCriteria criteria = new SearchCriteria();
		model.addAttribute("criteria", criteria);
		
		return "searchbook";
	}
	
	
	@RequestMapping("/retrievebook")
	public String retrieveBook(@ModelAttribute("criteria") SearchCriteria criteria, ModelMap model) {
		
		model.addAttribute("criteria", criteria);
		
		List<String> messages = new ArrayList<String>();
		
		validateSearchCriteria(messages, criteria);
		
		if(!messages.isEmpty()){
			model.addAttribute("messages", messages);
		} else {
		
			List<Book> booksList = searchBooks(criteria, messages);
			
			if(booksList == null) {
				model.addAttribute("infoMsg", "Sorry, there are no books available.");			
			} else if (booksList.isEmpty()) {
				model.addAttribute("infoMsg", "Sorry, book you have searched for does not exists.");			
			} else {
				model.addAttribute("books", booksList);
			}
		}
		
		return "searchbook";
		
	}
	
	@RequestMapping("/deletesearchbook")
	public String deleteSearchBookPage(Model model) {
		
		SearchCriteria criteria = new SearchCriteria();
		model.addAttribute("criteria", criteria);
		
		return "searchbookfordelete";
	}
	
	
	@RequestMapping(value="/deletebooksearch")
	public String searchBookForDelete(@ModelAttribute("criteria") SearchCriteria criteria, ModelMap model) {
		
		model.addAttribute("criteria", criteria);
		
		List<String> messages = new ArrayList<String>();
		
		validateSearchCriteria(messages, criteria);
		
		if(!messages.isEmpty()){
			model.addAttribute("messages", messages);
		} else {
		
			List<Book> booksList = searchBooks(criteria, messages);
			
			if(booksList == null) {
				model.addAttribute("infoMsg", "Sorry, there are no books available to Delete.");			
			} else if (booksList.isEmpty()) {
				model.addAttribute("infoMsg", "Sorry, book you have searched for does not exists.");			
			} else {
				model.addAttribute("books", booksList);
			}
		}
		
		return "searchbookfordelete";
		
	}
	
	
	@RequestMapping(value="/deletebook/{bookId}")
	public String deleteBook(@PathVariable("bookId") long bookId, ModelMap model) {
		
		List<String> messages = new ArrayList<String>();
		
		try {
			
			this.bookService.deleteBookById(bookId);
			
			model.addAttribute("successMsg", "Book with Id "+bookId+" has been deleted successfully");
			
		} catch(Exception exce) {
			
			exce.printStackTrace();
			messages.add("Error occured while deleting the book. Please try after sometime");
			model.addAttribute("messages", messages);
		}	
		
		SearchCriteria criteria = new SearchCriteria();
		model.addAttribute("criteria", criteria);		
		
		return "searchbookfordelete";
	}
	

	
	
	private void validateSearchCriteria(List<String> messages, SearchCriteria criteria) {
		
		if(null == criteria.getInputValue() || "".equalsIgnoreCase(criteria.getInputValue().trim())) {
			
			messages.add("Please enter the value on the text field to Search a Book");
			return;
		}
		
		if(criteria.getSearchBy().equalsIgnoreCase("bookId")) {
			
			String numberRegex ="\\d{1,}";
			
			if(!criteria.getInputValue().trim().matches(numberRegex)) {
				messages.add("Book Id is invalid and it should be a Number.");
			}
		}
	}
	
	
	private List<Book> searchBooks(SearchCriteria criteria, List<String> messages) {
		
		List<Book> booksList = null;
		
		try {	
				
			booksList = new ArrayList<Book>();
			
			Book searchedBook = null;
			
			if("bookId".equalsIgnoreCase(criteria.getSearchBy())) {
				
				searchedBook = this.bookService.searchBookById(Long.parseLong(criteria.getInputValue().trim()));
				
			} else if("bookTitle".equalsIgnoreCase(criteria.getSearchBy())) {
				searchedBook = this.bookService.searchBookByTitle(criteria.getInputValue().trim());
			}
			
			if(searchedBook != null) 
				booksList.add(searchedBook);
			
			
		} catch(Exception exce) {
			messages.add("Error occured while searching a book. Please try after sometime");
		}
		
		
		return booksList;
	}
	
	
	private Book filterBookById(long bookId) {
		
		Book filteredBook = null;
		
		if(this.bookSet != null && !this.bookSet.isEmpty()) {
		
			filteredBook = this.bookSet.stream()
				.filter(book -> bookId == book.getBookId().longValue())
				.findAny()
				.orElse(null);
		}
		
		return filteredBook;
	}
	
	private Book filterBookByTitle(String bookTitle) {
	
		Book filteredBook = null;
		
		if(this.bookSet != null && !this.bookSet.isEmpty()) {
		
			filteredBook = this.bookSet.stream()
				.filter(book -> book.getTitle().equalsIgnoreCase(bookTitle))
				.findAny()
				.orElse(null);
		}
		
		return filteredBook;
	}

}
