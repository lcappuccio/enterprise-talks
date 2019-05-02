package com.enterprise.talks.service;

import com.enterprise.talks.model.Participant;
import com.enterprise.talks.model.Talk;

import java.util.List;

public interface TalkService {

	/**
	 * Creates a {@link Talk} and returns the id
	 *
	 * @return
	 */
	String createTalk();

	/**
	 * Lists all {@link Talk}
	 *
	 * @return
	 */
	List<Talk> findAllTalks();

	/**
	 * Find a {@link Talk} given an id
	 *
	 * @param talkId
	 * @return
	 */
	Talk findById(final String talkId);

	/**
	 * List {@link Participant} of a given {@link Talk}
	 *
	 * @param talkId
	 * @return
	 */
	List<Participant> getTalkParticipants(final String talkId);

	/**
	 * Add {@link Participant} to a {@link Talk}
	 *
	 * @param talkId
	 * @param participant
	 */
	void addParticipant(final String talkId, final Participant participant);

	/**
	 * List inactive {@link Participant} of a {@link Talk}
	 *
	 * @param talkId
	 * @return
	 */
	List<Participant> getInactiveParticipants(final String talkId);

	/**
	 * Set a {@link Participant} as inactive for a given {@link Talk}
	 *
	 * @param talkId
	 * @param participant
	 */
	void setInactiveParticipant(final String talkId, final Participant participant);

	/**
	 * Extract a random participant from a given {@link Talk}
	 *
	 * @param talkId
	 * @return
	 */
	Participant extractRandomParticipantFrom(final String talkId);

}
