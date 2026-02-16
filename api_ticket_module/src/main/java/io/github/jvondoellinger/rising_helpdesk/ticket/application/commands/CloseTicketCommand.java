package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

/**
 *
 * @param ticketId ID do ticket
 * @param agentId ID do agente responsavel pela interação
 */
public record CloseTicketCommand(
	   TicketId ticketId,
	   UserProfileId agentId
) implements Command {
}
