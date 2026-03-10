package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record CreateQueueCommand(
		String area,
		String subarea,
		UserProfileId createdBy
)  implements Command {
}
