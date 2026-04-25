package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindQueueByIdService implements FindQueueByIdQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public ResultV1<QueueDetails, String> handle(FindQueueByIdQuery query) {
        var optional = repository.findById(query.id());

        if (optional.isEmpty()) {
            return ResultV1.failure("No queue found.");
        }

        var queue = optional.get();
        var details = mapper.details(queue);

        return ResultV1.success(details);
    }

    @Override
    public Class<FindQueueByIdQuery> getQueryType() {
        return FindQueueByIdQuery.class;
    }
}
