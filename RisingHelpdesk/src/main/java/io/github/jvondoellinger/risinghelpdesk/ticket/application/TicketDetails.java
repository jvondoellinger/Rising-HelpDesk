package io.github.jvondoellinger.risinghelpdesk.ticket.application;

import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Mention;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.valueObjects.TicketNumber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TicketDetails(
	   UUID id,
	   TicketNumber ticketNumber,
	   String title,
	   QueueDetails queue,
	   List<Mention> mentions,
	   LocalDateTime deadline,
	   UUID openedBy,
	   LocalDateTime openedOn,
	   UUID lastUpdatedBy,
	   LocalDateTime lastUpdatedOn
) {
}
