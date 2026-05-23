package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStepImpl;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByAuthorQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAuthorQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindQueueByAuthorService implements FindQueueByAuthorQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public ResultB<Pagination<QueueDetails>> handle(FindQueueByAuthorQuery query) {
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
    public Class<FindQueueByAuthorQuery> getQueryType() {
        return FindQueueByAuthorQuery.class;
    }
}
