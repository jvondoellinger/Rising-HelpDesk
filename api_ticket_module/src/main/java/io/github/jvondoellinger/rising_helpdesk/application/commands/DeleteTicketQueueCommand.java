package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
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
