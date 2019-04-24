package com.enterprise.talks.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotEquals;

public class ParticipantTest {

	private Participant sut;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_not_accept_null_firstName() {
		sut = new Participant();

		expectedException.expect(IllegalArgumentException.class);
		sut.setFirstName(null);
	}

	@Test
	public void should_not_accept_empty_firstName() {
		sut = new Participant();

		expectedException.expect(IllegalArgumentException.class);
		sut.setFirstName("");
	}

	@Test
	public void should_not_accept_null_lastName() {
		sut = new Participant();

		expectedException.expect(IllegalArgumentException.class);
		sut.setLastName(null);
	}

	@Test
	public void should_not_accept_empty_lastName() {
		sut = new Participant();

		expectedException.expect(IllegalArgumentException.class);
		sut.setLastName("");
	}

	@Test
	public void homonymous_participants_should_not_be_equal() {

		final Participant participantA = new Participant();
		final Participant participantB = new Participant();

		final String firstName = "Adam";
		final String lastName = "Smith";

		participantA.setFirstName(firstName);
		participantA.setLastName(lastName);
		participantB.setFirstName(firstName);
		participantB.setLastName(lastName);

		assertNotEquals(participantA, participantB);
	}
}
