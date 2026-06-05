package io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.state;

import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.status.TicketStatus;

public final class TicketStateFactory {
	public static TicketState from(TicketStatus status) {
		return switch (status) {
			case PENDING -> new TicketPendingState();
			case VALIDATING -> new TicketValidatingState();
			case COMPLETED -> new TicketCompletedState();
			case INCOMPLETE -> new TicketIncompleteState();
			case RETRYING -> new TicketRetryingState();
			case UNPRODUCTIVE -> new TicketUnproductiveState();
			case CANCELED -> new TicketCanceledState();
			case PRIORITIZED -> new TicketPrioritizedState();
			case CLOSED -> new TicketClosedState();
		};
	}
}
