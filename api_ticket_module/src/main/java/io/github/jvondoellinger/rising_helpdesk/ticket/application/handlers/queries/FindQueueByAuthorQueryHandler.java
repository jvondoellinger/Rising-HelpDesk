package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAuthorQuery;

public interface FindQueueByAuthorQueryHandler extends QueryHandler<FindQueueByAuthorQuery,Pagination<QueueDetails>> {
}
