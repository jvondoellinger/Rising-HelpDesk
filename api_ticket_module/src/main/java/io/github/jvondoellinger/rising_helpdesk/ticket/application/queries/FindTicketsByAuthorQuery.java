package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindTicketsByAuthorQuery(String author) implements Query {
}
