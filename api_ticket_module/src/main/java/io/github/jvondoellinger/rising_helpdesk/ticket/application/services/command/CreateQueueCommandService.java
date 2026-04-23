package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateQueueCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueCommandService implements CreateQueueCommandHandler {
	private final QueueRepository repository;
	private final QueueMapper mapper;
	private final CurrentUserService currentUserService;

	@Override
	public Result<Void, String> handle(CreateQueueCommand cmd) {
		if (cmd == null) {
			return Result.failure("Command is null.");
		}

		if (repository.existsByArea(cmd.area())) {
			return Result.failure("Area already exists.");
		}

		var queue = new Queue(
			   cmd.area(),
			   cmd.subarea(),
			   currentUserService.getUserId()
		);

		repository.save(queue);

		return Result.success();
	}

	@Override
	public Class<CreateQueueCommand> getCommandType() {
		return CreateQueueCommand.class;
	}
}
