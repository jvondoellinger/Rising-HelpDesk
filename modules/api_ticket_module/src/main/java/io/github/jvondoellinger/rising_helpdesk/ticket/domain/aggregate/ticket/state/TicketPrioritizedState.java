package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketPrioritizedState implements TicketState {
	private final TicketStatus status;

	protected TicketPrioritizedState() {
		status = TicketStatus.PRIORITIZED;
	}
	@Override
	public void validate(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.VALIDATING));
	}

	@Override
	public void complete(Ticket ticket) {
		throw new IllegalStateException("A prioritized ticket cannot be completed without validation.");
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw new IllegalStateException("A prioritized ticket cannot be marked as incomplete.");
	}

	@Override
	public void retry(Ticket ticket) {
		throw new IllegalStateException("A prioritized ticket cannot be retried.");
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw new IllegalStateException("A prioritized ticket cannot be marked as unproductive.");
	}

	@Override
	public void cancel(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.CANCELED));
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw new IllegalStateException("This ticket is already prioritized.");
	}

	@Override
	public void close(Ticket ticket) {
		throw new IllegalStateException("A prioritized ticket cannot be closed.");
	}

	@Override
	public TicketStatus getStatus() {
		return status ;
	}
}
