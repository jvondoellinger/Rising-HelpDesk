package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

public record CreateQueueCommand(
	   String area,
	   String subarea,
	   DomainId createdBy
)  implements Command {
}
