package io.github.jvondoellinger.rising_helpdesk.ticket.domain;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;

public class TicketId {
	private final DomainId id;

	private TicketId(String value) {
		this.id = DomainId.parse(value);
	}
	private TicketId(DomainId id) {
		this.id = id;
	}

	public static TicketId of(DomainId id) {
		return new TicketId(id);
	}
	public static TicketId of(String id) {
		return new TicketId(id);
	}

	private String value() {
		return id.value();
	}

	@Override
	public String toString() {
		return value();
	}
}
