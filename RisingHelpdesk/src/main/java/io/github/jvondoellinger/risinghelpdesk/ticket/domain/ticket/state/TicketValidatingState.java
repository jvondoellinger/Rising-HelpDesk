package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

public class TicketValidatingState implements TicketState {
	private final TicketStatus status;

	protected TicketValidatingState() {
		status = TicketStatus.VALIDATING;
	}
	@Override
	public void validate(Ticket ticket) {
		throw new RuntimeException("This ticket is already under validation.");
	}

	@Override
	public void complete(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.COMPLETED));
	}

	@Override
	public void markIncomplete(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.INCOMPLETE));
	}

	@Override
	public void retry(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.RETRYING));
	}

	@Override
	public void markUnproductive(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.UNPRODUCTIVE));
	}

	@Override
	public void cancel(Ticket ticket) {
		ticket.updateState(TicketStateFactory.from(TicketStatus.CANCELED));
	}

	@Override
	public void prioritize(Ticket ticket) {
		throw new RuntimeException("A validating ticket cannot be prioritized.");
	}

	@Override
	public void close(Ticket ticket) {
		throw new RuntimeException("A validating ticket cannot be closed directly.");
	}

	@Override
	public TicketStatus getStatus() {
		return status;
	}
}
