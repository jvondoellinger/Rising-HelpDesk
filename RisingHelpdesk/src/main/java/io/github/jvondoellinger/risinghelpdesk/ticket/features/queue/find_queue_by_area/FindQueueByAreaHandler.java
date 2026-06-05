package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_area;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.QueryHandler;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

public interface FindQueueByAreaHandler extends QueryHandler<FindQueueByArea, QueueDetails> {
}
