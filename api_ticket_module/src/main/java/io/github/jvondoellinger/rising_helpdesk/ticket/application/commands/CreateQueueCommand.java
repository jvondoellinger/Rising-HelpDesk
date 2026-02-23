package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record CreateQueueCommand(
	   String area,
	   String subarea,
	   DomainId createdBy
)  implements Command {
}
