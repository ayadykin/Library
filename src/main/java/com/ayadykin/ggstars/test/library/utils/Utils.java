package com.ayadykin.ggstars.test.library.utils;

import static com.ayadykin.ggstars.test.library.exception.LibraryException.ERROR_LESS_ID;
import static com.ayadykin.ggstars.test.library.exception.LibraryException.ERROR_GT_ID;
import com.ayadykin.ggstars.test.library.exception.LibraryException;;

public class Utils {

	public static long parseId(String value, String errorMessage) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			throw new LibraryException(errorMessage);
		}
	}

	public static void validateIdLess(long id) {
		if (id <= 0) {
			throw new LibraryException(ERROR_LESS_ID);
		}
	}

	public static void validateIdGt(long id) {
		if (id >= 0) {
			throw new LibraryException(ERROR_GT_ID);
		}
	}
}
