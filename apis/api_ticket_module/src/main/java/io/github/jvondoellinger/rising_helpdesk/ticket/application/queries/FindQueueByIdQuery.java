package io.github.jvondoellinger.rising_helpdesk.ticket.application.queries;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Query;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;

import java.util.UUID;

public record FindQueueByIdQuery(UUID id) implements Query<QueueDetails> {
}
