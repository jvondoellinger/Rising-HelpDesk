package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;

import java.util.UUID;

public record FindTicketByIdQuery(UUID id) implements Query<TicketDetails> {
}
