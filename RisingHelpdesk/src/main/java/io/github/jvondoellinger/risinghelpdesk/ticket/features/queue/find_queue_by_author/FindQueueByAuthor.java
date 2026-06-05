package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_author;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Query;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;

import java.util.UUID;

public record FindQueueByAuthor(UUID authorId, int page, int size) implements Query<Pagination<QueueDetails>> {
}
