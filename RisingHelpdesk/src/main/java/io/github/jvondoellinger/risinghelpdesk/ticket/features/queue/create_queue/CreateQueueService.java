package io.github.jvondoellinger.risinghelpdesk.ticket.features.queue.create_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Queue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueService implements CreateQueueHandler {
	private final QueueRepository repository;
	private final AuthenticatedUser authenticatedUser;

	@Override
	public ResultB handle(CreateQueue cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   if (cmd == null) {
					   return ResultB.error(new DomainError("COMMAND_IS_NULL", "Command is null."));
				   }

				   if (repository.existsByArea(cmd.area())) {
					   return ResultB.error(new DomainError("AREA_ALREADY_EXISTS", "Area already exists."));
				   }

				   var queue = new Queue(
						 cmd.area(),
						 cmd.subarea(),
						 authenticatedUser.getCurrentUserId()
				   );

				   repository.save(queue);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<CreateQueue> getCommandType() {
		return CreateQueue.class;
	}
}
