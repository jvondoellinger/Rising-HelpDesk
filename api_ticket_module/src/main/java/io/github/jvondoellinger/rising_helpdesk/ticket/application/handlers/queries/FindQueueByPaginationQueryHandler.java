package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;

public interface FindQueueByPaginationQueryHandler extends QueryHandler<FindQueueByPaginationQuery, Pagination<QueueDetails>> {
}
