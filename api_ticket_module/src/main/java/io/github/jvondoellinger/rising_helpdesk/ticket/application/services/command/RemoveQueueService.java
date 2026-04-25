package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.ResultV1;
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
	public ResultV1<Void, String> handle(RemoveQueueCommand cmd) {
		var optional = repository.findById(cmd.queueId());

		if (optional.isEmpty()) {
			return ResultV1.failure("No queue found.");
		}

		repository.delete(optional.get());

		return ResultV1.success();
	}

	@Override
	public Class<RemoveQueueCommand> getCommandType() {
		return RemoveQueueCommand.class;
	}
}
