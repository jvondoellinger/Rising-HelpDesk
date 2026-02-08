package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.domain.TicketId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

/**
 *
 * @param ticketId ID do ticket
 * @param text Texto presente na interação
 * @param agentId ID do agente responsavel pela interação
 */

public record InteractionTicketCommand(
	   TicketId ticketId,
	   String text,
	   UserProfileId agentId
) implements Command {
}
