package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketCompletedState implements TicketState {
	private final TicketStatus status;

	protected TicketCompletedState() {
		status = TicketStatus.COMPLETED;
	}
	@Override
	public void validate(Ticket ticket) {
		throw new RuntimeException("This ticket has already been completed and cannot be validated again.");
	}

	@Override
	public void complete(Ticket ticket) {
		throw new RuntimeException("This ticket has already been completed.");
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw new RuntimeException("A completed ticket cannot be marked as incomplete.");
	}

	@Override
	public void retry(Ticket ticket) {
		throw new RuntimeException("A completed ticket cannot be retried.");
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw new RuntimeException("A completed ticket cannot be marked as unproductive.");
	}

	@Override
	public void cancel(Ticket ticket) {
		throw new RuntimeException("A completed ticket cannot be canceled.");
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw new RuntimeException("A completed ticket cannot be prioritized.");
	}

	@Override
	public void close(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.CLOSED));
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
