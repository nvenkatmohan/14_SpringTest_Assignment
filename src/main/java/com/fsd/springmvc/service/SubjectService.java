package com.fsd.springmvc.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fsd.springmvc.dao.SubjectDao;
import com.fsd.springmvc.model.Subject;
import com.fsd.springmvc.util.SerializationUtil;
import com.fsd.springmvc.util.SubjectException;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectDao subjectDao;
	
	public Subject searchSubjectById(long subjectId) {
		
		List<Subject> subjectsList = this.subjectDao.findSubjectById(subjectId);
		
		return (subjectsList != null && !subjectsList.isEmpty()) 
				? subjectsList.get(0) : null;
	}
	
	public Subject searchSubjectByTitle(String subjectTitle) {
		
		List<Subject> subjectsList = this.subjectDao.findSubjectByTitle(subjectTitle);
		
		return (subjectsList != null && !subjectsList.isEmpty()) 
				? subjectsList.get(0) : null;
	}

	public void addSubject(Subject subject) {
		
		this.subjectDao.addSubject(subject);
	}

	
	public void deleteSubjectById(long subjectId) {
		
		this.subjectDao.deleteSubjectById(subjectId);
	}
}
