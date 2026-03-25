package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateQueueCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueCommandService implements CreateQueueCommandHandler {
	private final QueueRepository repository;
	private final QueueMapper mapper;

	@Override
	public Result<Void> handle(CreateQueueCommand cmd) {
		if (cmd == null) {
			return Result.failure("Command is null.");
		}

		if (repository.existsByArea(cmd.area())) {
			return Result.failure("Area already exists.");
		}

		var entity = mapper.from(cmd);
		repository.save(entity);

		return Result.success();
	}

	@Override
	public Class<CreateQueueCommand> getCommandType() {
		return CreateQueueCommand.class;
	}
}
