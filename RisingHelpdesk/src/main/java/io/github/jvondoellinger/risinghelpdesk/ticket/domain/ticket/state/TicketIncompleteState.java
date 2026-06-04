package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketIncompleteState implements TicketState {
	private final TicketStatus status;

	protected TicketIncompleteState() {
		status = TicketStatus.INCOMPLETE;
	}
	@Override
	public void validate(Ticket ticket) {
		throw new RuntimeException("An incomplete ticket cannot be validated.");
	}

	@Override
	public void complete(Ticket ticket) {
		throw new RuntimeException("An incomplete ticket cannot be completed.");
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw new RuntimeException("This ticket is already marked as incomplete.");
	}

	@Override
	public void retry(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.RETRYING));
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw new RuntimeException("An incomplete ticket cannot be marked as unproductive.");
	}

	@Override
	public void cancel(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.CANCELED));
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw new RuntimeException("An incomplete ticket cannot be prioritized.");
	}

	@Override
	public void close(Ticket ticket) {
		throw new RuntimeException("An incomplete ticket cannot be closed.");
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
