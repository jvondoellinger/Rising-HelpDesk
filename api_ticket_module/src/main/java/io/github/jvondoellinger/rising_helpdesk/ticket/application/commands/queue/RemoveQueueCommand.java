package io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param queueId ID da fila
 * @param agentId ID do usuario que esrtá disparando o evento
 */
public record RemoveQueueCommand(
        UUID queueId
) implements Command {
}
