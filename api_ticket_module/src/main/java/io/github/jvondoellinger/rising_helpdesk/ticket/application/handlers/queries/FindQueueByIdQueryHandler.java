package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;

public interface FindQueueByIdQueryHandler extends QueryHandler<FindQueueByIdQuery, QueueDetails> {
}
