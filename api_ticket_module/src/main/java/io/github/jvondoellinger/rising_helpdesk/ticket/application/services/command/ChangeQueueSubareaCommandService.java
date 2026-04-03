package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
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
	public Result<Void> handle(ChangeQueueSubareaCommand cmd) {
		var optional = repository.findById(cmd.id());

		if (optional.isEmpty()) {
			return Result.failure("No queue found.");
		}

		var queue = optional.get();
		var subarea = cmd.subarea();

		if (queue.getArea().equals(subarea)) {
			return Result.failure("The queue already has this subarea.");
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

		return Result.success();
	}

	@Override
	public Class<ChangeQueueSubareaCommand> getCommandType() {
		return ChangeQueueSubareaCommand.class;
	}
}
