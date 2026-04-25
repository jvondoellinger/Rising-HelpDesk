package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param queueId ID da fila que o ticket será delegado
 */
public record DelegateTicketCommand(
	   UUID ticketId,
	   UUID queueId
) implements Command {
}
