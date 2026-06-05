package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_number;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.valueObjects.TicketNumber;

public record FindTicketByNumberQuery(TicketNumber number) implements Query<TicketDetails> {
}
