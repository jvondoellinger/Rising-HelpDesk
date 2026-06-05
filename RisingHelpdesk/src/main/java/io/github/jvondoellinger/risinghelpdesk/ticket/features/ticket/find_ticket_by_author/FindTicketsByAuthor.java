package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_author;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;

import java.util.UUID;

public record FindTicketsByAuthor(UUID author, int page, int size) implements Query<Pagination<TicketDetails>> {
}
