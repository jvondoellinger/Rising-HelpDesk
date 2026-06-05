package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_ticket_mention;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

public record AddTicketMention(UUID userId,
						 UUID ticketId
) implements Command {
}
