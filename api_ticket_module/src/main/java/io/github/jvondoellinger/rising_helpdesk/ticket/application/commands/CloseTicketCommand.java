package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param agentId  ID do agente responsavel pela interação
 */
public record CloseTicketCommand(
        UUID ticketId,
        UUID agentId
) implements Command {
}
