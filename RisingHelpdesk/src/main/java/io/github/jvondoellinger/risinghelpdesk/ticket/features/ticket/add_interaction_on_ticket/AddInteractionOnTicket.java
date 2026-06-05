package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_on_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param text     Texto presente na interação
 */

public record AddInteractionOnTicket(
	   String text,
        UUID ticketId
) implements Command {
}
