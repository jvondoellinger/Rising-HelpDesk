package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;

public record FindQueueByPaginationQuery(int page, int size) implements Query<Pagination<QueueDetails>> {
}
