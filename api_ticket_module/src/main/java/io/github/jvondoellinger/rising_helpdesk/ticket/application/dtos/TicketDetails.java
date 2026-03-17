package io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record TicketDetails(
	   TicketNumber ticketNumber,
	   String title,
	   UUID queueId,
	   List<Mention> mentions,
	   LocalDateTime deadline,
	   UUID openedBy,
	   LocalDateTime openedOn,
	   UUID lastUpdatedBy,
	   LocalDateTime lastUpdatedOn
) {
}
