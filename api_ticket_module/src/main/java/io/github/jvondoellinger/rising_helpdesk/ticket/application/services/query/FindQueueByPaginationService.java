package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByPaginationQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindQueueByPaginationService implements FindQueueByPaginationQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public ResultV1<Pagination<QueueDetails>, String> handle(FindQueueByPaginationQuery query) {
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

        return ResultV1.success(detailsPagination);
    }

    @Override
    public Class<FindQueueByPaginationQuery> getQueryType() {
        return FindQueueByPaginationQuery.class;
    }
}
