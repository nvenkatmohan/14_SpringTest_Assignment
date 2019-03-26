package com.fsd.springmvc.util;

public class StringUtils {

	
	final static String EMPTY_STRING = "";
	
	public static String trimToEmpty(String inputString) {
		
		if(null != inputString) {
			return inputString.trim();
		}
		
		return EMPTY_STRING;
	}
}
