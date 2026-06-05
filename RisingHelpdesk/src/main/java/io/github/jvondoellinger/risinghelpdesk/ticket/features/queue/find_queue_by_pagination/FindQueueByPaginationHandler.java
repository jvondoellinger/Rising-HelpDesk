package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_pagination;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.QueryHandler;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

public interface FindQueueByPaginationHandler extends QueryHandler<FindQueueByPagination, Pagination<QueueDetails>> {
}
