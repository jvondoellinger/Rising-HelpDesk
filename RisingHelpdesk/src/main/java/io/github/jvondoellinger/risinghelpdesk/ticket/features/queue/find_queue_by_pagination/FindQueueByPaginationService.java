package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_pagination;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.PaginationFilter;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueDetails;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.QueueMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindQueueByPaginationService implements FindQueueByPaginationHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    @Cacheable(value = "queue-by-pagination", key = "#query.page() + ':' + #query.size()")
    public ResultB<Pagination<QueueDetails>> handle(FindQueueByPagination query) {
        return ResultB.create()
                .flatMap(aVoid -> {
                    var queuePagination = repository.findByPagination(PaginationFilter.of(
                            query.page(),
                            query.size()
                    ));

                    var details = queuePagination
                            .items()
                            .stream()
                            .map(mapper::details)
                            .toList();

                    var detailsPagination = Pagination.of(details,
                            query.page(),
                            queuePagination.totalPages()
                    );

                    return ResultB.of(detailsPagination);
                });
    }

    @Override
    public Class<FindQueueByPagination> getQueryType() {
        return FindQueueByPagination.class;
    }
}
