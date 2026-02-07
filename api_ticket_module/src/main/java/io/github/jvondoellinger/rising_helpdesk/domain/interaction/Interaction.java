package io.github.jvondoellinger.rising_helpdesk.domain.interaction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.InteractionId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.time.LocalDateTime;

public class Interaction {
	private final InteractionId id;
	private final String text;

	private final boolean visible;

	private final UserProfileId interactedBy;
	private final LocalDateTime interactedOn;

	public Interaction(InteractionId id, String text, boolean visible, UserProfileId interactedBy, LocalDateTime interactedOn) {
		this.id = id;
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = interactedOn;
	}

	public Interaction(String text, boolean visible, UserProfileId interactedBy) {
		this.id = new InteractionId();
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = LocalDateTime.now();
	}
	public Interaction(String text, UserProfileId interactedBy) {
		this.id = new InteractionId();
		this.text = text;
		this.visible = true;
		this.interactedBy = interactedBy;
		this.interactedOn = LocalDateTime.now();
	}

	public InteractionId getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public boolean isVisible() {
		return visible;
	}
	public UserProfileId getInteractedBy() {
		return interactedBy;
	}
	public LocalDateTime getInteractedOn() {
		return interactedOn;
	}
}
