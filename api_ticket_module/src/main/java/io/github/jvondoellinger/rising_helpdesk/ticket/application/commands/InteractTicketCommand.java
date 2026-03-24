package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

/**
 *
 * @param ticketId ID do ticket
 * @param text     Texto presente na interação
 * @param agentId  ID do agente responsavel pela interação
 */

public record InteractTicketCommand(
        UUID ticketId,
        String text,
        UUID agentId
) implements Command {
}
