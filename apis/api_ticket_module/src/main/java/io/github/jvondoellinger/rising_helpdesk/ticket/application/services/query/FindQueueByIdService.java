package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStepImpl;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindQueueByIdService implements FindQueueByIdQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public ResultB<QueueDetails> handle(FindQueueByIdQuery query) {
         return ResultB.create()
                .flatMap(aVoid -> {
                    var optional = repository.findById(query.id());

                    if (optional.isEmpty()) {
                        return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
                    }

                    var queue = optional.get();
                    var details = mapper.details(queue);

                    return ResultB.of(details);
                });

    }

    @Override
    public Class<FindQueueByIdQuery> getQueryType() {
        return FindQueueByIdQuery.class;
    }
}
