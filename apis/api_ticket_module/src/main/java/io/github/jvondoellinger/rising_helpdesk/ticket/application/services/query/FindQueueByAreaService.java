package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStepImpl;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByAreaQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAreaQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindQueueByAreaService implements FindQueueByAreaQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public ResultB<QueueDetails> handle(FindQueueByAreaQuery query) {
        return ResultB.create()
                .flatMap(aVoid -> {
                    var optional = repository.findBySubarea(query.area());

                    if (optional.isEmpty()) {
                        return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
                    }

                    var queue = optional.get();
                    var mapped = mapper.details(queue);

                    return ResultB.create().map(v -> mapped);
                });
    }

    @Override
    public Class<FindQueueByAreaQuery> getQueryType() {
        return FindQueueByAreaQuery.class;
    }
}
