package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_author;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.QueryHandler;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;

public interface FindTicketsByAuthorQueryHandler extends QueryHandler<FindTicketsByAuthor, Pagination<TicketDetails>> {
}
