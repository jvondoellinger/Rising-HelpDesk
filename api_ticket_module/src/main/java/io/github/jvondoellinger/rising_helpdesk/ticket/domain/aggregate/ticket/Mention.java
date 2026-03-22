package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mention {
	private final UUID id;
	private final UUID userId;
	private final UUID mentionedById;
	private final LocalDateTime mentionedAt;

	public Mention(UUID userId, UUID mentionedId) {
		this.id = UUID.randomUUID();
		this.mentionedById = mentionedId;
		this.userId = userId;
		this.mentionedAt = LocalDateTime.now();
	}
	public Mention(UUID id, UUID userId, UUID mentionedId) {
		this.id = id;
		this.userId = userId;
		this.mentionedById = mentionedId;
		this.mentionedAt = LocalDateTime.now();
	}
	public Mention(UUID id, UUID userId, UUID mentionedId, LocalDateTime mentionedAt) {
		this.id = id;
		this.userId = userId;
		this.mentionedById = mentionedId;
		this.mentionedAt = mentionedAt;
	}

	public LocalDateTime getMentionedAt() {
		return mentionedAt;
	}
	public UUID getUserId() {
		return userId;
	}
	public UUID getId() {
		return id;
	}
	public UUID getMentionedById() {
		return mentionedById;
	}
}
