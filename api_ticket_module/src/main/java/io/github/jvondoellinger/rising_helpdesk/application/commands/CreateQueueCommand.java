package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;

public record CreateQueueCommand(
	   String area,
	   String subarea,
	   DomainId createdBy
) {
}
