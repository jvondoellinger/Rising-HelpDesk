package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.RemoveQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.RemoveQueueHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class RemoveQueueService implements RemoveQueueHandler {
	private final QueueRepository repository;

	@Override
	public ResultTransformerStep<Void> handle(RemoveQueueCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var optional = repository.findById(cmd.queueId());

				   if (optional.isEmpty()) {
					   return Result.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
				   }

				   repository.delete(optional.get());

				   return Result.success();
			   });
	}

	@Override
	public Class<RemoveQueueCommand> getCommandType() {
		return RemoveQueueCommand.class;
	}
}
