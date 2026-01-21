package com.github.jvondoellinger.agp_protocol.ticket_module.application.queries;

import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.mention.Mentions;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.valueObjects.TicketNumber;

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
