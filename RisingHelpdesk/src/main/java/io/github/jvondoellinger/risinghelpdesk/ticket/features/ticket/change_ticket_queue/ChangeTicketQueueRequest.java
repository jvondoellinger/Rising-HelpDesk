package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.change_ticket_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param queueId ID da fila que o ticket será delegado
 */
public record ChangeTicketQueue(
	   UUID ticketId,
	   UUID queueId
) implements Command {
}
