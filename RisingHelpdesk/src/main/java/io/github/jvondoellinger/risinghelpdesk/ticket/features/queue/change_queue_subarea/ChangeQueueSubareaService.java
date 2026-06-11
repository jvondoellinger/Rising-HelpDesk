package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.change_queue_subarea;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Queue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangeQueueSubareaService implements ChangeQueueSubareaHandler {
	private final QueueRepository repository;
	private final AuthenticatedUser authenticatedUser;

	@Override
	public ResultB handle(ChangeQueueSubarea cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.id());

				   if (optional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
				   }

				   var queue = optional.get();
				   var subarea = cmd.subarea();

				   if (queue.getArea().equals(subarea)) {
					   return ResultB.error(new DomainError("THE_QUEUE_ALREADY_HAS_THIS_SUBAREA", "The queue already has this subarea."));
				   }

				   var updated = new Queue(
						 queue.getId(),
						 queue.getArea(),
						 subarea,
						 queue.getCreatedBy(),
						 queue.getUpdatedAt(),
						 LocalDateTime.now(),
						 authenticatedUser.getCurrentUserId()
				   );

				   repository.save(updated);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<ChangeQueueSubarea> getCommandType() {
		return ChangeQueueSubarea.class;
	}
}
