package com.github.jvondoellinger.agp_protocol.ticket_module.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.QueueId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.dtos.MentionsDto;

import java.time.LocalDateTime;

public record CreateTicketCommand(
	   String title,
	   QueueId queueId,
	   MentionsDto mentions,
	   LocalDateTime deadline,
	   UserProfileId openedBy
) {
}
