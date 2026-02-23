package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;

public interface FindTicketByPaginationQueryHandler extends QueryHandler<FindTicketByPaginationQuery, Pagination<TicketDetails>> {
}
