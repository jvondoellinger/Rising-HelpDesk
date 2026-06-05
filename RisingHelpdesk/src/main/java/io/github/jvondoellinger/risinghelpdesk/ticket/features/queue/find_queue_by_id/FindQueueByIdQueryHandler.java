package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_id;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.QueryHandler;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

public interface FindQueueByIdQueryHandler extends QueryHandler<FindQueueById, QueueDetails> {
}
