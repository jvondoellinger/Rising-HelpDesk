package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.close_ticket;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 */
public record CloseTicketRequest(
        UUID ticketId
) {
}
