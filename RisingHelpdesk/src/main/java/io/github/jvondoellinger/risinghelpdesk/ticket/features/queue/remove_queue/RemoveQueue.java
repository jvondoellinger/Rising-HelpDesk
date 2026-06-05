package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.remove_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

/**
 *
 * @param queueId ID da fila
 */
public record RemoveQueue(
        UUID queueId
) implements Command {
}
