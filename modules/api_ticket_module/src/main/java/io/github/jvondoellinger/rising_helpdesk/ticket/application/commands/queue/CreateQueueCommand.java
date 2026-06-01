package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Command;

public record CreateQueueCommand(
		String area,
		String subarea
)  implements Command {
}
