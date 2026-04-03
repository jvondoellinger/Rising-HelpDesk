package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

/**
 *
 * @param queueId ID da fila
 * @param agentId ID do usuario que esrtá disparando o evento
 */
public record DeleteQueueCommand(
        UUID queueId
) implements Command {
}
