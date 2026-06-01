package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateQueueCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.QueueMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class CreateQueueCommandService implements CreateQueueCommandHandler {
	private final QueueRepository repository;
	private final QueueMapper mapper;
	private final CurrentUserService currentUserService;

	@Override
	public ResultB<Void> handle(CreateQueueCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   if (cmd == null) {
					   return ResultB.error(new DomainError("COMMAND_IS_NULL", "Command is null."));
				   }

				   if (repository.existsByArea(cmd.area())) {
					   return ResultB.error(new DomainError("AREA_ALREADY_EXISTS", "Area already exists."));
				   }

				   var queue = new Queue(
						 cmd.area(),
						 cmd.subarea(),
						 currentUserService.getUserId()
				   );

				   repository.save(queue);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<CreateQueueCommand> getCommandType() {
		return CreateQueueCommand.class;
	}
}
