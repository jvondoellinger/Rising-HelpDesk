package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;

public interface FindTicketByIdQueryHandler extends QueryHandler<FindTicketByIdQuery, TicketDetails> {
}
