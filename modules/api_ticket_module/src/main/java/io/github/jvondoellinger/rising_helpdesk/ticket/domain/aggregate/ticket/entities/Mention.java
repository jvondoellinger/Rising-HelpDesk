package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mention {
	private final UUID id;
	private final UUID userId;
	private final UUID mentionedById;
	private final LocalDateTime mentionedAt;
	private final UUID ticketId; // referencia ao root

	public Mention(UUID userId, UUID mentionedById, UUID ticketId) {
		this.ticketId = ticketId;
		this.id = UUID.randomUUID();
		this.mentionedById = mentionedById;
		this.userId = userId;
		this.mentionedAt = LocalDateTime.now();
	}
	public Mention(UUID id, UUID userId, UUID mentionedById, UUID ticketId) {
		this.id = id;
		this.userId = userId;
		this.mentionedById = mentionedById;
		this.ticketId = ticketId;
		this.mentionedAt = LocalDateTime.now();
	}
	public Mention(UUID id, UUID userId, UUID mentionedById, LocalDateTime mentionedAt, UUID ticketId) {
		this.id = id;
		this.userId = userId;
		this.mentionedById = mentionedById;
		this.mentionedAt = mentionedAt;
		this.ticketId = ticketId;
	}

	public UUID getId() {
		return id;
	}
	public UUID getUserId() {
		return userId;
	}
	public UUID getMentionedById() {
		return mentionedById;
	}
	public LocalDateTime getMentionedAt() {
		return mentionedAt;
	}
	public UUID getTicketId() {
		return ticketId;
	}
}
