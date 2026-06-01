package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketUnproductiveState implements TicketState {
	private final TicketStatus status;

	protected TicketUnproductiveState() {
		status = TicketStatus.UNPRODUCTIVE;
	}
	@Override
	public void validate(Ticket ticket) {
		throw new RuntimeException("An unproductive ticket cannot be validated.");
	}

	@Override
	public void complete(Ticket ticket) {
		throw new RuntimeException("An unproductive ticket cannot be completed.");
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		throw new RuntimeException("An unproductive ticket cannot be marked as incomplete.");
	}

	@Override
	public void retry(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.RETRYING));
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		throw new RuntimeException("This ticket is already marked as unproductive.");
	}

	@Override
	public void cancel(Ticket ticket) {
		throw new RuntimeException("An unproductive ticket cannot be canceled.");
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw new RuntimeException("An unproductive ticket cannot be prioritized.");
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
