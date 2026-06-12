package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_author;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.PaginationFilter;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindQueueByAuthorService implements FindQueueByAuthorHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    @Cacheable(value = "queue-by-author", key = "#query.authorId()")
    public ResultB<Pagination<QueueDetails>> handle(FindQueueByAuthor query) {
        return ResultB.create()
                .flatMap(aVoid -> {
                    var pagination = repository.findByAuthor(query.authorId(), PaginationFilter.of(query.page(), query.size()));

                    if (pagination.isEmpty()) {
                        return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
                    }

                    var detailsPagination = mapper.detailsPagination(pagination);

                    return ResultB.of(detailsPagination);
                });
    }

    @Override
    public Class<FindQueueByAuthor> getQueryType() {
        return FindQueueByAuthor.class;
    }
}
