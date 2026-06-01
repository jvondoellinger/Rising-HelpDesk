package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketPendingState implements TicketState {
	private final TicketStatus status;
	protected TicketPendingState() {
		status = TicketStatus.PENDING;
	}
	@Override
	public void validate(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.VALIDATING));
	}

	@Override
	public void complete(Ticket ticket) {
		throw new RuntimeException("This ticket in progress cannot be completed without being validated.");
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw new RuntimeException("This ticket in progress cannot be marked as incomplete if it has not been validated.");
	}

	@Override
	public void retry(Ticket ticket) {
		throw new RuntimeException("This ticket in progress cannot be retried if it has not been validated.");
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw new RuntimeException("It is not possible to mark a ticket as unproductive if it has not been validated.");
	}

	@Override
	public void cancel(Ticket ticket) {
		throw new RuntimeException("It is not possible to cancel a ticket that has not been validated.");
	}

	@Override
	public void prioritize(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.PRIORITIZED));
	}

	@Override
	public void close(Ticket ticket) {
		throw new RuntimeException("It is not possible to move directly to completed without going through validation.");
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
