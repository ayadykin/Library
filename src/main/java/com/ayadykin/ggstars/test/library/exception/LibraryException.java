package com.ayadykin.ggstars.test.library.exception;

public class LibraryException extends RuntimeException {
	
	public final static String ERROR_PARSE_BOOK_ID = "Error parse book id";
	public final static String ERROR_PARSE_AUTHOR_ID = "Error parse author id";
	public final static String ERROR_ID = "Id can't be empty";
	public final static String SERVICE_ERROR = "Service error";
	
	public LibraryException(String errorCode) {
		super(errorCode);
	}
}
