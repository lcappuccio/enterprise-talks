package com.enterprise.talks.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Talk {

	private final String talkId;
	private final List<Participant> participants = new ArrayList<>();
	private final List<Participant> inactiveParticipants = new ArrayList<>();

	public Talk() {
		this.talkId = UUID.randomUUID().toString();
	}

	public String getTalkId() {
		return talkId;
	}

	public List<Participant> getParticipants() {
		return Collections.unmodifiableList(participants);
	}

	public void addParticipant(final Participant participant) {
		if (participant == null || participants.contains(participant)) {
			throw new IllegalArgumentException();
		}
		participants.add(participant);
	}

	public List<Participant> getInactiveParticipants() {
		return Collections.unmodifiableList(inactiveParticipants);
	}

	public void setInactiveParticipant(final Participant participant) {
		if (participant == null || inactiveParticipants.contains(participant)) {
			throw new IllegalArgumentException();
		}
		inactiveParticipants.add(participant);
	}
}
