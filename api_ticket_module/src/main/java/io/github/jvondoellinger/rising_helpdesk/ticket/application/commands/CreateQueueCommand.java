package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;

public record CreateQueueCommand(
		String area,
		String subarea
)  implements Command {
}
