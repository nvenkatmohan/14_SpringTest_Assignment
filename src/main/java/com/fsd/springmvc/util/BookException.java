package com.fsd.springmvc.util;

public class BookException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2345678L;

	public BookException() {
		super();
	}
	
	public BookException(String errorMessage) {
		super(errorMessage);
	}
}
