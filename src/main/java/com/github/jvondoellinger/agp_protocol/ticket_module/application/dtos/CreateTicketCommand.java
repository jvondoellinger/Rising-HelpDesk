package com.github.jvondoellinger.agp_protocol.ticket_module.application.dtos;


import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueId;

import java.time.LocalDateTime;

public record CreateTicketCommand(
	   String title,
	   QueueId queueId,
	   MentionsDTO mentions,
	   LocalDateTime deadline,
	   UserProfileId openedBy) {
}
