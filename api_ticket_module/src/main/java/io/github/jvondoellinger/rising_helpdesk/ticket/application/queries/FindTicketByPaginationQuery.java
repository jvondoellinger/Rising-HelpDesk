package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;

public record FindTicketByPaginationQuery(int page, int limit) implements Query<Pagination<TicketDetails>> {
}
