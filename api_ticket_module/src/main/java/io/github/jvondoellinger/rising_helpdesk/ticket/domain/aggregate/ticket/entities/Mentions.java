package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainCollection;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.util.List;
import java.util.UUID;

public class Mentions extends DomainCollection<UserProfileId> {
	private final UUID id;

	public Mentions(List<UserProfileId> profiles) {
		super(profiles);
		this.id = UUID.randomUUID();
	}
	public Mentions(UUID id, List<UserProfileId> profiles) {
		super(profiles);
		this.id = id;

	}
	public Mentions() {
        this.id = UUID.randomUUID();
    }

	public UUID getId() {
		return id;
	}
}
