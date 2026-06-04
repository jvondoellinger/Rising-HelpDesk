package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status;

// Status do chamado
public enum TicketStatus {
	PENDING,
	VALIDATING,
	COMPLETED,
	INCOMPLETE,
	RETRYING,
	UNPRODUCTIVE,
	CANCELED,
	PRIORITIZED,
	CLOSED;
}
