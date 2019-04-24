package com.enterprise.talks.model;

import com.enterprise.talks.utils.StringUtils;

public class Participant {

	private String firstName;
	private String lastName;

	public Participant() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		StringUtils.validateString(firstName);
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		StringUtils.validateString(lastName);
		this.lastName = lastName;
	}
}
