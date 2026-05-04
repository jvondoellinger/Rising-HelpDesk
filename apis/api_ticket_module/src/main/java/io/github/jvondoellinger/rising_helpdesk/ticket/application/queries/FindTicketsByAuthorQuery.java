package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;

import java.util.UUID;

public record FindTicketsByAuthorQuery(UUID author, int page, int size) implements Query<Pagination<TicketDetails>> {
}
