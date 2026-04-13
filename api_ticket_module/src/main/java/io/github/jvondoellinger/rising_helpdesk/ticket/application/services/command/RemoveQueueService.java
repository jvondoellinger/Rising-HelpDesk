package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.RemoveQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.RemoveQueueHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveQueueService implements RemoveQueueHandler {
	private final QueueRepository repository;

	@Override
	public Result<Void> handle(RemoveQueueCommand cmd) {
		var optional = repository.findById(cmd.queueId());

		if (optional.isEmpty()) {
			return Result.failure("No queue found.");
		}

		repository.delete(optional.get());

		return Result.success();
	}

	@Override
	public Class<RemoveQueueCommand> getCommandType() {
		return RemoveQueueCommand.class;
	}
}
