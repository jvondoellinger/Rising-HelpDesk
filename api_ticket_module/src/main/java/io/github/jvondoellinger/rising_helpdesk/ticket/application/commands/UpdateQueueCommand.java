package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record UpdateQueueCommand(
	   QueueId id,
	   String area,
	   String subarea,
	   UserProfileId agentId
) implements Command {
}
