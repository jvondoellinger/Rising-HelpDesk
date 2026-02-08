package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

public record UpdateQueueCommand(
	   QueueId id,
	   String area,
	   String subarea,
	   UserProfileId agentId
) implements Command {
}
