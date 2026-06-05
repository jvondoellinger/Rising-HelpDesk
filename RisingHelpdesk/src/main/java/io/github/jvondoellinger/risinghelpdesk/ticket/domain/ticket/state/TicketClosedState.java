package io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.state;

import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.Ticket;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.status.TicketStatus;

public class TicketClosedState implements TicketState {
	private final TicketStatus status;
	protected TicketClosedState() {
		status = TicketStatus.CLOSED;
	}
	private RuntimeException error() {
		return new IllegalStateException("Cannot perform this action on a closed ticket.");
	}

	@Override
	public void validate(Ticket ticket) {
		throw error();
	}

	@Override
	public void complete(Ticket ticket) {
		throw error();
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw error();
	}

	@Override
	public void retry(Ticket ticket) {
		throw error();
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw error();
	}

	@Override
	public void cancel(Ticket ticket) {
		throw error();
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw error();
	}

	@Override
	public void close(Ticket ticket) {
		throw new IllegalStateException("This ticket is already closed.");
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
