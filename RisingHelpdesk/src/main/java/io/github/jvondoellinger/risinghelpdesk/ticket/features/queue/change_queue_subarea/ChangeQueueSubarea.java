package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_subarea;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

public record ChangeQueueSubarea(UUID id, String subarea) implements Command {
}
