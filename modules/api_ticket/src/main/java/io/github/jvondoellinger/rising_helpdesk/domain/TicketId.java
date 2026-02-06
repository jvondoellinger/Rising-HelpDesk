package com.github.jvondoellinger.agp_protocol.api_ticket.domain;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;

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
