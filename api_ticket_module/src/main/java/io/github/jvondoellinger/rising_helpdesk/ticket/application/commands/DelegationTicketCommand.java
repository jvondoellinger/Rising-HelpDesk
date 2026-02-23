package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

/**
 *
 * @param ticketId ID do ticket
 * @param queueId ID da fila que o ticket será delegado
 * @param agentId ID do agente responsavel pela interação
 */
public record DelegationTicketCommand(
	   TicketId ticketId,
	   QueueId queueId,
	   UserProfileId agentId
) implements Command {
}
