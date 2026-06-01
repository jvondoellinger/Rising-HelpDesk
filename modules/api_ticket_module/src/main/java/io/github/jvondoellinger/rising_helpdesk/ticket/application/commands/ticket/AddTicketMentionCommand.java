package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.Command;

import java.util.UUID;

public record AddTicketMentionCommand(UUID userId,
							   UUID ticketId
) implements Command {
}
