package com.enterprise.talks.service;

import com.enterprise.talks.model.Participant;
import com.enterprise.talks.model.Talk;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.*;

public class TalkServiceImplTest {

	private TalkService sut = new TalkServiceImpl();

	private Participant participantA, participantB, inactiveParticipant;

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
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_validate_id() {
		final TalkServiceImpl talkService = new TalkServiceImpl();

		expectedException.expect(IllegalArgumentException.class);
		talkService.validateTalkId("NON_EXISTING_ID");
	}

	@Test
	public void should_create_talk_with_id_and_store_it() {

		final String talkId = sut.createTalk();

		final Talk talk = sut.findById(talkId);

		assertNotNull(talk);
		assertEquals(talkId, talk.getTalkId());
		assertEquals(1, sut.findAllTalks().size());
	}

	@Test
	public void should_return_null_when_talk_id_not_found() {

		final Talk nonExistingTalk = sut.findById("NON_EXISTING_ID");

		assertNull(nonExistingTalk);
	}

	@Test
	public void should_add_talk_participants_and_return_list() {

		final String talkId = createTalk();
		sut.addParticipant(talkId, participantA);
		sut.addParticipant(talkId, participantB);

		final List<Participant> talkParticipants = sut.getTalkParticipants(talkId);

		assertEquals(2, talkParticipants.size());
	}

	@Test
	public void should_return_unmodifiable_participants_list() {

		final String talkId = createTalk();
		final List<Participant> talkParticipants = sut.getTalkParticipants(talkId);

		expectedException.expect(UnsupportedOperationException.class);
		talkParticipants.clear();
	}

	@Test
	public void should_return_unmodifiable_inactive_participants_list() {

		final String talkId = createTalk();
		final List<Participant> inactiveParticipants = sut.getInactiveParticipants(talkId);

		expectedException.expect(UnsupportedOperationException.class);
		inactiveParticipants.clear();
	}

	@Test
	public void should_set_talk_inactive_participants() {

		final String talkId = createTalk();
		sut.setInactiveParticipant(talkId, participantA);
		sut.setInactiveParticipant(talkId, participantB);

		final List<Participant> inactiveParticipants = sut.getInactiveParticipants(talkId);

		assertEquals(2, inactiveParticipants.size());
	}

	@Test
	public void should_not_add_talk_participants_to_nonexisting_talk() {

		expectedException.expect(IllegalArgumentException.class);
		sut.addParticipant("NON_EXISTING_TALK_ID", new Participant());
	}

	@Test
	public void should_not_get_nonexisting_talk() {

		expectedException.expect(IllegalArgumentException.class);
		sut.getTalkParticipants("NON_EXISTING_TALK_ID");
	}

	@Test
	public void should_not_add_talk_inactive_participants_to_nonexisting_talk() {

		expectedException.expect(IllegalArgumentException.class);
		sut.setInactiveParticipant("NON_EXISTING_TALK_ID", new Participant());
	}

	@Test
	public void should_not_get_inactive_participants_to_nonexisting_talk() {

		expectedException.expect(IllegalArgumentException.class);
		sut.getInactiveParticipants("NON_EXISTING_TALK_ID");
	}

	@Test
	public void should_return_random_participant() {

		final String talkId = createTalk();

		sut.addParticipant(talkId, participantA);
		sut.addParticipant(talkId, participantB);
		sut.setInactiveParticipant(talkId, inactiveParticipant);

		final Participant randomParticipant = sut.extractRandomParticipantFrom(talkId);

		assertNotNull(randomParticipant);
		assertNotEquals(inactiveParticipant, randomParticipant);
	}

	@Test
	public void should_not_return_random_participant_from_nonexisting_talk() {

		expectedException.expect(IllegalArgumentException.class);
		sut.extractRandomParticipantFrom("NON_EXISTING_TALK_ID");
	}


	private String createTalk() {
		return sut.createTalk();
	}
}
