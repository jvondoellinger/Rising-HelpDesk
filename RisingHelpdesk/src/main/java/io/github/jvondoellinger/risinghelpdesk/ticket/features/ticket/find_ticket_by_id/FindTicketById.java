package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_id;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;

import java.util.UUID;

public record FindTicketById(UUID id) implements Query<TicketDetails> {
}
