package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_pagination;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

public record FindQueueByPagination(int page, int size) implements Query<Pagination<QueueDetails>> {
}
