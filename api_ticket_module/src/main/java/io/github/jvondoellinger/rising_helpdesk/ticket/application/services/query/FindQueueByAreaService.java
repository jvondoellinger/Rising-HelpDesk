package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindQueueByAreaQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByAreaQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindQueueByAreaService implements FindQueueByAreaQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    public ResultV1<QueueDetails, String> handle(FindQueueByAreaQuery query) {
        var optional = repository.findBySubarea(query.area());

        if (optional.isEmpty()) {
            return ResultV1.failure("No queue found.");
        }

        var queue = optional.get();
        var mapped = mapper.details(queue);

        return ResultV1.success(mapped);
    }

    @Override
    public Class<FindQueueByAreaQuery> getQueryType() {
        return FindQueueByAreaQuery.class;
    }
}
