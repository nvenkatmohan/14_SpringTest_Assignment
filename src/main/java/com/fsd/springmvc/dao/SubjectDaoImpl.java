package com.fsd.springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fsd.springmvc.extractor.SubjectExtractor;
import com.fsd.springmvc.model.Book;
import com.fsd.springmvc.model.Subject;
import com.fsd.springmvc.util.QueryConstants;

@Repository
@Qualifier("subjectDao")
public class SubjectDaoImpl implements SubjectDao {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public List<Subject> findAllSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> findSubjectById(long subjectId) {
		
		List<Subject> subjectList = this.jdbcTemplate.query(
				QueryConstants.SELECT_SUBJECT_BY_ID_SQL, 
				new Object[]{subjectId}, new SubjectExtractor());
		
		return subjectList;
	}

	@Override
	public List<Subject> findSubjectByTitle(String subjectTitle) {

		List<Subject> subjectList = this.jdbcTemplate.query(
				QueryConstants.SELECT_SUBJECT_BY_TITLE_SQL, 
				new Object[]{subjectTitle}, new SubjectExtractor());
		
		return subjectList;
	}

	@Override
	public void addSubject(Subject subject) {
		
		if(subject != null && subject.getBooks() != null)
		{
			for(Book book : subject.getBooks())
			{
				this.jdbcTemplate.update(QueryConstants.INSERT_SUBJECT_SQL, 
						new Object[]{subject.getSubjectId(), book.getBookId(),
								subject.getSubjectTitle(),subject.getDurationHours() });
			}
		}
	}

	@Override
	public void deleteSubjectById(long subjectId) {
		
		this.jdbcTemplate.update(QueryConstants.DELETE_SUBJECT_SQL,
				new Object[]{subjectId});		
	}
}
