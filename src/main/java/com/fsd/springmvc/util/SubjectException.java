package com.fsd.springmvc.util;

public class SubjectException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 234567821459L;

	public SubjectException() {
		super();
	}
	
	public SubjectException(String errorMessage) {
		super(errorMessage);
	}

}
