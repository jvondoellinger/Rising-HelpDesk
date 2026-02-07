package io.github.jvondoellinger.rising_helpdesk.application.queries;

import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.domain.mention.Mentions;
import io.github.jvondoellinger.rising_helpdesk.domain.valueObjects.TicketNumber;
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
