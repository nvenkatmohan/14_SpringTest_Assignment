package com.fsd.springmvc.dao;

import java.util.List;

import com.fsd.springmvc.model.Subject;

public interface SubjectDao {

	public List<Subject> findAllSubjects();
	
	public List<Subject> findSubjectById(long subjectId);
	
	public List<Subject> findSubjectByTitle(String title);
	
	public void addSubject(Subject subject);
	
	public void deleteSubjectById(long subjectId);
}
