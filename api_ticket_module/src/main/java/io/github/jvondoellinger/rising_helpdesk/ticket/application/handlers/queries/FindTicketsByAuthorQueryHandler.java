package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.QueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketsByAuthorQuery;

public interface FindTicketsByAuthorQueryHandler extends QueryHandler<FindTicketsByAuthorQuery, Pagination<TicketDetails>> {
}
