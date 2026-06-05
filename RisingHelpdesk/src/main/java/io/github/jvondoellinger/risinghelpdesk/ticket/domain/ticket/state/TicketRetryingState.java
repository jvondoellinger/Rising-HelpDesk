package io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.state;

import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.Ticket;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.status.TicketStatus;

public class TicketRetryingState implements TicketState {
	private final TicketStatus status;

	protected TicketRetryingState() {
		status = TicketStatus.RETRYING;
	}
	@Override
	public void validate(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.VALIDATING));
	}

	@Override
	public void complete(Ticket ticket) {
		throw new RuntimeException("A retrying ticket cannot be completed directly.");
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw new RuntimeException("A retrying ticket cannot be marked as incomplete.");
	}

	@Override
	public void retry(Ticket ticket) {
		throw new RuntimeException("This ticket is already in retrying state.");
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw new RuntimeException("A retrying ticket cannot be marked as unproductive.");
	}

	@Override
	public void cancel(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.CANCELED));
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw new RuntimeException("A retrying ticket cannot be prioritized.");
	}

	@Override
	public void close(Ticket ticket) {
		throw new RuntimeException("A retrying ticket cannot be closed.");
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
