package io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.state;

import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.Ticket;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.status.TicketStatus;

public interface TicketState {
  	void validate(Ticket ticket);
	void complete(Ticket ticket);
	void markIncomplete(Ticket ticket);
	void retry(Ticket ticket);
	void markUnproductive(Ticket ticket);
	void cancel(Ticket ticket);
	void prioritize(Ticket ticket);
	void close(Ticket ticket);

	TicketStatus getStatus();
}
