package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_pagination;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;

public record FindTicketsByPagination(int page, int size) implements Query<Pagination<TicketDetails>> {
}
