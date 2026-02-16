package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

/**
 *
 * @param queueId ID da fila
 * @param agentId ID do usuario que esrtá disparando o evento
 */
public record DeleteTicketQueueCommand(
	   QueueId queueId,
	   UserProfileId agentId)  implements Command {
}
