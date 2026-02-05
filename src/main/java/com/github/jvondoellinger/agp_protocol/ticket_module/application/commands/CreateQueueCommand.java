package com.github.jvondoellinger.agp_protocol.ticket_module.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;

public record CreateQueueCommand(
	   String area,
	   String subarea,
	   DomainId createdBy
) {
}
