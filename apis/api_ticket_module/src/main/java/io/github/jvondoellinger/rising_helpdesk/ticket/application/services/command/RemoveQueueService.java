package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.RemoveQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.RemoveQueueHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class RemoveQueueService implements RemoveQueueHandler {
	private final QueueRepository repository;

	@Override
	public ResultB<Void> handle(RemoveQueueCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.queueId());

				   if (optional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
				   }

				   repository.delete(optional.get());

				   return ResultB.create();
			   });
	}

	@Override
	public Class<RemoveQueueCommand> getCommandType() {
		return RemoveQueueCommand.class;
	}
}
