package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ChangeQueueSubareaCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.ChangeQueueSubareaCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ChangeQueueSubareaCommandService implements ChangeQueueSubareaCommandHandler {
	private final QueueRepository repository;
	private final CurrentUserService currentUserService;

	@Override
	public ResultV1<Void, String> handle(ChangeQueueSubareaCommand cmd) {
		var optional = repository.findById(cmd.id());

		if (optional.isEmpty()) {
			return ResultV1.failure("No queue found.");
		}

		var queue = optional.get();
		var subarea = cmd.subarea();

		if (queue.getArea().equals(subarea)) {
			return ResultV1.failure("The queue already has this subarea.");
		}

		var updated = new Queue(
			   queue.getId(),
			   queue.getArea(),
			   subarea,
			   queue.getCreatedBy(),
			   queue.getUpdatedAt(),
			   LocalDateTime.now(),
			   currentUserService.getUserId()
		);

		repository.save(updated);

		return ResultV1.success();
	}

	@Override
	public Class<ChangeQueueSubareaCommand> getCommandType() {
		return ChangeQueueSubareaCommand.class;
	}
}
