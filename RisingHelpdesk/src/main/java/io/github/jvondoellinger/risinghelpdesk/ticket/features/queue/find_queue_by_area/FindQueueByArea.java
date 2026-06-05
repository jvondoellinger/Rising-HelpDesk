package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_area;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

public record FindQueueByArea(String area) implements Query<QueueDetails> {
}
