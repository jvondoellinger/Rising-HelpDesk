package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_area;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.util.UUID;

public record ChangeQueueArea(UUID id, String area) implements Command {
}
