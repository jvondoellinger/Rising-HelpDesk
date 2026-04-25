package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;

public interface FindQueueByIdQueryHandler extends QueryHandler<FindQueueByIdQuery, QueueDetails> {
}
