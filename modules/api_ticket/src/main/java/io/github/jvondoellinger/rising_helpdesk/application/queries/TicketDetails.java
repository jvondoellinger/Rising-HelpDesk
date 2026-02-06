package com.github.jvondoellinger.agp_protocol.api_ticket.application.queries;

import com.github.jvondoellinger.agp_protocol.api_ticket.domain.QueueId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.valueObjects.TicketNumber;

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
