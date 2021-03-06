package com.enterprise.talks.service;

import com.enterprise.talks.model.Participant;
import com.enterprise.talks.model.Talk;

import java.util.*;

public class TalkServiceImpl implements TalkService {

	private final HashMap<String, Talk> talks = new HashMap<>();

	public TalkServiceImpl() {
	}

	@Override
	public String createTalk() {
		final Talk talk = new Talk();
		final String talkId = talk.getTalkId();
		talks.put(talkId, talk);
		return talkId;
	}

	@Override
	public List<Talk> findAllTalks() {
		return Collections.list(Collections.enumeration(talks.values()));
	}

	@Override
	public Talk findById(String talkId) {
		return talks.get(talkId);
	}

	@Override
	public List<Participant> getTalkParticipants(String talkId) {
		validateTalkId(talkId);
		return Collections.unmodifiableList(talks.get(talkId).getParticipants());
	}

	@Override
	public void addParticipant(String talkId, Participant participant) {
		validateTalkId(talkId);
		talks.get(talkId).addParticipant(participant);
	}

	@Override
	public List<Participant> getInactiveParticipants(final String talkId) {
		validateTalkId(talkId);
		return Collections.unmodifiableList(talks.get(talkId).getInactiveParticipants());
	}

	@Override
	public void setInactiveParticipant(final String talkId, final Participant participant) {
		validateTalkId(talkId);
		talks.get(talkId).setInactiveParticipant(participant);
	}

	@Override
	public Participant extractRandomParticipantFrom(final String talkId) {

		validateTalkId(talkId);

		final List<Participant> participants = new ArrayList<>(talks.get(talkId).getParticipants());
		participants.removeAll(getInactiveParticipants(talkId));
		final Random random = new Random();

		final int totalParticipants = participants.size();
		final int randomParticipantIndex = random.nextInt(totalParticipants);

		return participants.get(randomParticipantIndex);
	}

	void validateTalkId(final String talkId) {
		if (!talks.containsKey(talkId)) {
			throw new IllegalArgumentException();
		}
	}
}
