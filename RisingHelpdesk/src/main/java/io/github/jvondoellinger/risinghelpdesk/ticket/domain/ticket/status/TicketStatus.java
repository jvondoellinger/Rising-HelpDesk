package io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.status;

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
