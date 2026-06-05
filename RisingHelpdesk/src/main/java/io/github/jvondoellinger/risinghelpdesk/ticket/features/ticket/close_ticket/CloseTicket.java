package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.close_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 */
public record CloseTicket(
        UUID ticketId
) implements Command {
}
