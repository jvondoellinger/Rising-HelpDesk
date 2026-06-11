package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_number;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.QueryHandler;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;

public interface FindTicketByNumberHandler extends QueryHandler<FindTicketByNumber, TicketDetails> {
}
