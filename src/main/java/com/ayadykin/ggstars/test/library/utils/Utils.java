package com.ayadykin.ggstars.test.library.utils;

import static com.ayadykin.ggstars.test.library.exception.LibraryException.ERROR_ID;

import com.ayadykin.ggstars.test.library.exception.LibraryException;;

public class Utils {

	public static long parseId(String value, String errorMessage) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			throw new LibraryException(errorMessage);
		}
	}

	public static void validateId(long id) {
		if (id <= 0) {
			throw new LibraryException(ERROR_ID);
		}
	}
}
