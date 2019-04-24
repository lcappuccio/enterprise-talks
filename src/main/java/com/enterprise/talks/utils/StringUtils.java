package com.enterprise.talks.utils;

public class StringUtils {

	public static void validateString(final String string) {
		if (string == null || string.isEmpty()) {
			throw  new IllegalArgumentException();
		}
	}
}
