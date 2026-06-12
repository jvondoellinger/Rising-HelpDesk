package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_id;

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
public class FindQueueByIdService implements FindQueueByIdQueryHandler {
    private final QueueRepository repository;
    private final QueueMapper mapper;

    @Override
    @Cacheable(value = "queue-by-id", key = "#query.id()")
    public ResultB<QueueDetails> handle(FindQueueById query) {
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
    public Class<FindQueueById> getQueryType() {
        return FindQueueById.class;
    }
}
