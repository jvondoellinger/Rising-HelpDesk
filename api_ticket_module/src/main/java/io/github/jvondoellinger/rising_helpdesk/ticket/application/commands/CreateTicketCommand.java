package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicketCommand(
	   String title,
	   UUID queueId,
	   LocalDateTime deadline
) implements Command {
}
