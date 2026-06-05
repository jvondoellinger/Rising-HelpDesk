package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.remove_ticket_mention;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

public record RemoveTicketMention(UUID userId,
						    UUID ticketId
) implements Command {

}