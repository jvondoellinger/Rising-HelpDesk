package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param queueId ID da fila que o ticket será delegado
 * @param agentId ID do agente responsavel pela interação
 */
public record DelegationTicketCommand(
	   UUID ticketId,
	   UUID queueId,
	   UUID agentId
) implements Command {
}
