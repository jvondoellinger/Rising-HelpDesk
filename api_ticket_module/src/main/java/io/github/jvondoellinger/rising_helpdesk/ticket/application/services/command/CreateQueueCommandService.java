package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateQueueCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueRepository;
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
			return new Result.Failure<>(new KernelException("Command is null."));
		}

		if (repository.existsByArea(cmd.area())) {
			return new Result.Failure<>(new KernelException("Area already exists."));
		}

		var entity = mapper.from(cmd);
		repository.save(entity);

		return new Result.Success<>(null);
	}

	@Override
	public Class<CreateQueueCommand> getCommandType() {
		return CreateQueueCommand.class;
	}
}
