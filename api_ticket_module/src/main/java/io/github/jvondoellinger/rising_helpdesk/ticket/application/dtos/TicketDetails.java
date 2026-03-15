package io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mentions;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.time.LocalDateTime;

public record TicketDetails(
	   TicketNumber ticketNumber,
	   String title,
	   QueueId queueId,
	   Mentions mentions,
	   LocalDateTime deadline,
	   UserProfileId openedBy,
	   LocalDateTime openedOn,
	   UserProfileId lastUpdatedBy,
	   LocalDateTime lastUpdatedOn
) {
}
