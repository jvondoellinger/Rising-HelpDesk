package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.find_queue_by_area;

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
public class FindQueueByAreaService implements FindQueueByAreaHandler {
	private final QueueRepository repository;
	private final QueueMapper mapper;

	@Override
	@Cacheable(value = "queue-by-area", key = "#query.area()")
	public ResultB<QueueDetails> handle(FindQueueByArea query) {
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
	public Class<FindQueueByArea> getQueryType() {
		return FindQueueByArea.class;
	}
}
