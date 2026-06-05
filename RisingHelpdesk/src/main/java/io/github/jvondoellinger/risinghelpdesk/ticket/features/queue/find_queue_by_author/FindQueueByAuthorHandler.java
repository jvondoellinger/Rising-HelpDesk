package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_author;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.QueryHandler;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

public interface FindQueueByAuthorHandler extends QueryHandler<FindQueueByAuthor, Pagination<QueueDetails>> {
}
