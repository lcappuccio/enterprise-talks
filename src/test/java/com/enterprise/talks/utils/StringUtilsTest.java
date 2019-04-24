package com.enterprise.talks.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringUtilsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void empty_strings_are_invalid() {

		expectedException.expect(IllegalArgumentException.class);
		StringUtils.validateString("");
	}

	@Test
	public void null_strings_are_invalid() {

		expectedException.expect(IllegalArgumentException.class);
		//noinspection ConstantConditions
		StringUtils.validateString(null);
	}
}
