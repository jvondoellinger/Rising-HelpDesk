package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mention {
	private final UUID id;
	private final UUID userId;
	private final LocalDateTime mentionedAt;

	public Mention(UUID userId) {
		this.id = UUID.randomUUID();
		this.userId = userId;
		this.mentionedAt = LocalDateTime.now();
	}

	public Mention(UUID id, UUID userId) {
		this.id = id;
		this.userId = userId;
		this.mentionedAt = LocalDateTime.now();
	}

	public Mention(UUID id, UUID userId, LocalDateTime mentionedAt) {
		this.id = id;
		this.userId = userId;
		this.mentionedAt = mentionedAt;
	}
}
