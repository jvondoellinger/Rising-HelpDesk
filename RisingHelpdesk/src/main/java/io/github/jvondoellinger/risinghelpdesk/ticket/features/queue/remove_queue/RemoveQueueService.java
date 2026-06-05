package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.remove_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveQueueService implements RemoveQueueHandler {
	private final QueueRepository repository;

	@Override
	public ResultB<Void> handle(RemoveQueue cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.queueId());

				   if (optional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
				   }

				   repository.delete(optional.get());

				   return ResultB.create();
			   });
	}

	@Override
	public Class<RemoveQueue> getCommandType() {
		return RemoveQueue.class;
	}
}
