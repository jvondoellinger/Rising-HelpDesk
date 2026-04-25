package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByAreaQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAreaQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindQueueByAreaService implements FindQueueByAreaQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public Result<QueueDetails> handle(FindQueueByAreaQuery query) {
        var optional = repository.findBySubarea(query.area());

        if (optional.isEmpty()) {
            return Result.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
        }

        var queue = optional.get();
        var mapped = mapper.details(queue);

        return Result.success(mapped);
    }

    @Override
    public Class<FindQueueByAreaQuery> getQueryType() {
        return FindQueueByAreaQuery.class;
    }
}
