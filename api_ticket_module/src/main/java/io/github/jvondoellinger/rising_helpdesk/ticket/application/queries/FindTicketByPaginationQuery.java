package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Query;

public record FindTicketByPaginationQuery(int page, int size) implements Query {
}
