package com.github.jvondoellinger.agp_protocol.api_ticket.application.commands;

import com.github.jvondoellinger.agp_protocol.api_ticket.domain.QueueId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.mention.Mentions;

import java.time.LocalDateTime;

public record CreateTicketCommand(
	   String title,
	   QueueId queueId,
	   Mentions mentions,
	   LocalDateTime deadline,
	   UserProfileId openedBy
) {
}
