package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param agentId  ID do agente responsavel pela interação
 */
public record CloseTicketCommand(
        UUID ticketId
) implements Command {
}
