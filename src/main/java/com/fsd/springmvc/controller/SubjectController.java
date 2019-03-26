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
import com.fsd.springmvc.model.Subject;
import com.fsd.springmvc.service.BookService;
import com.fsd.springmvc.service.SubjectService;
import com.fsd.springmvc.util.BookException;
import com.fsd.springmvc.util.SubjectException;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
	
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private BookService bookService;
	
	private Set<Subject> subjectSet;
	
	private Set<Book> bookSet;
	
	
	@RequestMapping("/addsubject")
	public String addASubject(Model model) {
		
		Subject newSubject = new Subject();
		model.addAttribute("subject", newSubject);
		
		return "addsubject";
	}
	
	@RequestMapping("/savesubject")
	public String saveSubject(@Valid Subject newSubject, BindingResult result, ModelMap model) {
		
		model.addAttribute("subject", newSubject);
		
		if(!result.hasErrors()) {
			
			List<String> messages = new ArrayList<String>();
			
			try {
			
				List<Book> booksList = this.bookService.retriveAllBooks();
				
				if(null != booksList && !booksList.isEmpty())
					this.bookSet = new HashSet<Book>(booksList); 
				else
					this.bookSet = null;
				
				StringBuilder errorMessage = new StringBuilder();
				
				validateSubjectDetails(newSubject, errorMessage);
				
				if(errorMessage.length() > 0) {
					
					messages.add(errorMessage.toString());
				
				} else {
					
					validateBooks(newSubject, newSubject.getBookTitles(), errorMessage);
					
					if(errorMessage.length() > 0) {
						
						messages.add(errorMessage.toString());
						
					} else {
						
						this.subjectService.addSubject(newSubject);
						
						model.addAttribute("successMsg", "Subject has been added succcessfully");
					}
				
				}
			
			} catch (Exception exce) {
				messages.add(exce.getMessage());
			}
			
			if(!messages.isEmpty())
				model.addAttribute("messages", messages);
		}		
		
		return "addsubject";
	}
	
	
	private void validateSubjectDetails(Subject subject, StringBuilder errorMessage) {
		
		Subject searchedSubject = this.subjectService.searchSubjectById(subject.getSubjectId());
		
		if(searchedSubject == null) {
			
			searchedSubject = this.subjectService.searchSubjectByTitle(subject.getSubjectTitle());
			
			if(searchedSubject != null) 
				errorMessage.append("Subject Title '"+subject.getSubjectTitle()+"' already exists. "
						+ "Please try saving Subject with some other title");		
			
		} else {
			
			errorMessage.append("Subject Id '"+subject.getSubjectId()+"' already exists. "
					+ "Please try saving Subject with other Subject Id");
		}
		 
	}
	
	
	private void validateBooks(Subject subject, String referencedBooks, StringBuilder errorMessage) {
		
		if(!referencedBooks.isEmpty()) {
						
			if(this.bookSet != null && !this.bookSet.isEmpty()) {
				
			
				Set<Book> referencedBooksSet = new HashSet<Book>();
								
				String[] bookTitles = referencedBooks.split(",");
				
				StringBuilder invalidBookTitles = new StringBuilder();
				
				for(String bkTitle : bookTitles) {
					
					boolean isBookExists = false;
					
					for(Book book : this.bookSet) {
						
						if(book.getTitle().equalsIgnoreCase(bkTitle.trim())) {
							isBookExists = true;
							referencedBooksSet.add(book);
							break;
						}
					}
					
					if(!isBookExists)
						invalidBookTitles.append("\""+bkTitle+"\", ");
				}
				
				if(invalidBookTitles.length() > 0) {
					errorMessage.append("Book(s) "
								+invalidBookTitles.toString().substring(0, invalidBookTitles.length()-2)
								+" you have referenced does not exists.\n");
				} else {
					subject.setBooks(referencedBooksSet);
				}
				
				
				
			} else {
				
				errorMessage.append("There are NO Books available. Please add the Books first and then refer the same here");
			}
		}
	}
	
	@RequestMapping("/searchsubject")
	public String searchASubject(Model model) {
		
		SearchCriteria criteria = new SearchCriteria();
		model.addAttribute("criteria", criteria);
		
		return "searchsubject";
	}
	
	
	@RequestMapping("/retrievesubject")
	public String retrieveSubject(@ModelAttribute("criteria") SearchCriteria criteria, ModelMap model) {
		
		model.addAttribute("criteria", criteria);
		
		List<String> messages = new ArrayList<String>();
		
		validateSearchCriteria(messages, criteria);
		
		if(!messages.isEmpty()){
			model.addAttribute("messages", messages);
		} else {
		
			List<Subject> subjectsList = searchSubjects(criteria, messages);
			
			if(subjectsList == null) {
				model.addAttribute("infoMsg", "Sorry, there are no subjects available.");			
			} else if (subjectsList.isEmpty()) {
				model.addAttribute("infoMsg", "Sorry, the Subject you have searched for does not exists.");			
			} else {
				model.addAttribute("subjects", subjectsList);
			}
		}
		
		return "searchsubject";
		
	}
	
	
	private void validateSearchCriteria(List<String> messages, SearchCriteria criteria) {
		
		if(null == criteria.getInputValue() || "".equalsIgnoreCase(criteria.getInputValue().trim())) {
			
			messages.add("Please enter the value on the text field to Search a Subject");
			return;
		}
		
		if(criteria.getSearchBy().equalsIgnoreCase("subjectId")) {
			
			String numberRegex ="\\d{1,}";
			
			if(!criteria.getInputValue().trim().matches(numberRegex)) {
				messages.add("Subject Id is invalid and it should be a Number.");
			}
		}
	}
	
	
	private List<Subject> searchSubjects(SearchCriteria criteria, List<String> messages) {
		
		List<Subject> subjectsList = null;
		
		try {	
				
			subjectsList = new ArrayList<Subject>();
			
			Subject searchedSubject = null;
			
			if("subjectId".equalsIgnoreCase(criteria.getSearchBy())) {
				
				searchedSubject = this.subjectService.searchSubjectById((Long.parseLong(criteria.getInputValue().trim())));
				
			} else if("subjectTitle".equalsIgnoreCase(criteria.getSearchBy())) {
				
				searchedSubject = this.subjectService.searchSubjectByTitle(criteria.getInputValue().trim());
			}
			
			if(searchedSubject != null) 
				subjectsList.add(searchedSubject);
			
			
		} catch(Exception exce) {
			messages.add("Error occured while searching a subject. Please try after sometime");
		}
		
		
		return subjectsList;
	}
	
	@RequestMapping("/deletesearchsubject")
	public String deleteSearchBookPage(Model model) {
		
		SearchCriteria criteria = new SearchCriteria();
		model.addAttribute("criteria", criteria);
		
		return "searchsubjectfordelete";
	}
	
	
	@RequestMapping(value="/deletesubjectsearch")
	public String searchSubjectForDelete(@ModelAttribute("criteria") SearchCriteria criteria, ModelMap model) {
		
		model.addAttribute("criteria", criteria);
		
		List<String> messages = new ArrayList<String>();
		
		validateSearchCriteria(messages, criteria);
		
		if(!messages.isEmpty()){
			model.addAttribute("messages", messages);
		} else {
		
			List<Subject> subjectsList = searchSubjects(criteria, messages);
			
			if(subjectsList == null) {
				model.addAttribute("infoMsg", "Sorry, there are no Subjects available to Delete.");			
			} else if (subjectsList.isEmpty()) {
				model.addAttribute("infoMsg", "Sorry, subject you have searched for does not exists.");			
			} else {
				model.addAttribute("subjects", subjectsList);
			}
		}
		
		return "searchsubjectfordelete";
		
	}
	
	
	@RequestMapping(value="/deletesubject/{subjectId}")
	public String deleteSubject(@PathVariable("subjectId") long subjectId, ModelMap model) {
		
		List<String> messages = new ArrayList<String>();
		
		try {
			
			this.subjectService.deleteSubjectById(subjectId);
			
			model.addAttribute("successMsg", "Subject with Id "+subjectId+" has been deleted successfully");
			
		} catch(Exception exce) {
			
			messages.add("Error occured while deleting the subject. Please try after sometime");
			model.addAttribute("messages", messages);
		}	
		
		SearchCriteria criteria = new SearchCriteria();
		model.addAttribute("criteria", criteria);		
		
		return "searchsubjectfordelete";
	}
	
	
	private Subject filterSubjectById(long subjectId) {
		
		Subject filteredSubject = null;
		
		if(this.subjectSet != null && !this.subjectSet.isEmpty()) {
		
			filteredSubject = this.subjectSet.stream()
				.filter(sub -> subjectId == sub.getSubjectId().longValue())
				.findAny()
				.orElse(null);
		}
		
		return filteredSubject;
	}
	
	private Subject filterSubjectByTitle(String subjectTitle) {
	
		Subject filteredSubject = null;
		
		if(this.subjectSet != null && !this.subjectSet.isEmpty()) {
		
			filteredSubject = this.subjectSet.stream()
				.filter(sub -> sub.getSubjectTitle().equalsIgnoreCase(subjectTitle))
				.findAny()
				.orElse(null);
		}
		
		return filteredSubject;
	}

}
