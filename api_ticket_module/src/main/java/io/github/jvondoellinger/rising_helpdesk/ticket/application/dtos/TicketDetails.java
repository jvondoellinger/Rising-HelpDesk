package io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

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
