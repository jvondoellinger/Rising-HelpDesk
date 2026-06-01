package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketCanceledState implements TicketState {
	protected final TicketStatus status;
	protected TicketCanceledState() {
		status = TicketStatus.CANCELED;
	}

	private RuntimeException error() {
		return new IllegalStateException("Cannot perform this action on a canceled ticket.");
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
		throw new IllegalStateException("This ticket is already canceled.");
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw error();
	}

	@Override
	public void close(Ticket ticket) {
		throw error();
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
