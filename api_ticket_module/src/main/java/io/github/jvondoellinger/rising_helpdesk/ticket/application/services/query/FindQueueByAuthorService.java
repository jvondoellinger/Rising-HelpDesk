package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByAuthorQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAuthorQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindQueueByAuthorService implements FindQueueByAuthorQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public Result<Pagination<QueueDetails>> handle(FindQueueByAuthorQuery query) {
        var pagination = repository.findByAuthor(query.authorId(), PaginationFilter.of(query.page(), query.size()));

        if (pagination.isEmpty()) {
            return Result.failure("No queue found.");
        }

        var detailsPagination = mapper.detailsPagination(pagination);

        return Result.success(detailsPagination);
    }

    @Override
    public Class<FindQueueByAuthorQuery> getQueryType() {
        return FindQueueByAuthorQuery.class;
    }
}
