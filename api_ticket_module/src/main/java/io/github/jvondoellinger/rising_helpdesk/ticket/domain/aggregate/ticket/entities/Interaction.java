package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Interaction {
	private final UUID id;
	private final String text;

	private final boolean visible;

	private final UUID interactedBy;
	private final LocalDateTime interactedOn;

	private final UUID ticketId; // referencia ao root

	public Interaction(UUID id, String text, boolean visible, UUID interactedBy, LocalDateTime interactedOn, UUID ticketId) {
		this.id = id;
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = interactedOn;
		this.ticketId = ticketId;
	}

	public Interaction(String text, boolean visible, UUID interactedBy, UUID ticketId) {
		this.id = UUID.randomUUID();
		this.text = text;
		this.visible = visible;
		this.interactedBy = interactedBy;
		this.interactedOn = LocalDateTime.now();
		this.ticketId = ticketId;
	}
	public Interaction(String text, UUID interactedBy, UUID ticketId) {
		this.id = UUID.randomUUID();
		this.text = text;
		this.visible = true;
		this.interactedBy = interactedBy;
		this.interactedOn = LocalDateTime.now();
		this.ticketId = ticketId;
	}

	public UUID getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public boolean isVisible() {
		return visible;
	}
	public UUID getInteractedBy() {
		return interactedBy;
	}
	public LocalDateTime getInteractedOn() {
		return interactedOn;
	}
	public UUID getTicketId() {
		return ticketId;
	}
}
