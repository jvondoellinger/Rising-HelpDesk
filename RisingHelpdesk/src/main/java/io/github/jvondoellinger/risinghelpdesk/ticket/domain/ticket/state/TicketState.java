package io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.state;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.status.TicketStatus;

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
