package com.enterprise.talks.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class TalkTest {

	private Talk sut = new Talk();

	private Participant participantA, participantB, inactiveParticipant;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		participantA = new Participant();
		participantA.setFirstName("firstName_A");
		participantA.setLastName("lastName_A");

		participantB = new Participant();
		participantB.setFirstName("firstName_B");
		participantB.setLastName("lastName_B");

		inactiveParticipant = new Participant();
		inactiveParticipant.setFirstName("inactiveFirstName");
		inactiveParticipant.setLastName("inactiveLastName");

		sut.addParticipant(participantA);
		sut.addParticipant(participantB);

		sut.setInactiveParticipant(inactiveParticipant);
	}

	@Test
	public void should_have_id() {
		sut = new Talk();
		final String id = sut.getTalkId();

		assertNotNull(id);
	}

	@Test
	public void should_have_two_participants() {

		final List<Participant> participantsList = sut.getParticipants();

		assertEquals(2, participantsList.size());
		assertTrue(participantsList.contains(participantA));
		assertTrue(participantsList.contains(participantB));
	}

	@Test
	public void should_have_one_inactive_participant() {

		final List<Participant> inactiveParticipantsList = sut.getInactiveParticipants();

		assertEquals(1, inactiveParticipantsList.size());
		assertTrue(inactiveParticipantsList.contains(inactiveParticipant));
	}

	@Test
	public void should_not_accept_null_participant() {

		expectedException.expect(IllegalArgumentException.class);
		sut.addParticipant(null);
	}

	@Test
	public void should_not_add_twice_same_participant() {

		expectedException.expect(IllegalArgumentException.class);
		sut.addParticipant(participantA);
	}

	@Test
	public void should_not_accept_null_inactive_participant() {

		expectedException.expect(IllegalArgumentException.class);
		sut.setInactiveParticipant(null);
	}

	@Test
	public void should_not_add_twice_same_inactive_participant() {

		expectedException.expect(IllegalArgumentException.class);
		sut.setInactiveParticipant(inactiveParticipant);
	}

	@Test
	public void participant_list_is_unmodifiable() {

		expectedException.expect(UnsupportedOperationException.class);
		sut.getParticipants().clear();
	}

	@Test
	public void inactive_participant_list_is_unmodifiable() {

		expectedException.expect(UnsupportedOperationException.class);
		sut.getInactiveParticipants().clear();
	}
}
