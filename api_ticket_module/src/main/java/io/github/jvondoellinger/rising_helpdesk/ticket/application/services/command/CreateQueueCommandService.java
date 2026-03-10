package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateQueueCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueCommandService implements CreateQueueCommandHandler {
	private final QueueRepository repository;

	@Override
	public Result<Void> handle(CreateQueueCommand cmd) {
		return null;
	}

	@Override
	public Class<CreateQueueCommand> getType() {
		return CreateQueueCommand.class;
	}
}
