package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_id;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;
import java.util.UUID;

public record FindQueueById(UUID id) implements Query<QueueDetails> {
}
