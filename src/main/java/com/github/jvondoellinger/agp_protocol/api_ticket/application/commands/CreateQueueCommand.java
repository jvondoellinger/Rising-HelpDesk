package com.github.jvondoellinger.agp_protocol.api_ticket.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;

public record CreateQueueCommand(
	   String area,
	   String subarea,
	   DomainId createdBy
) {
}
