package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_area;

import java.util.UUID;

public record ChangeQueueAreaRequest(UUID id, String area) {
}
