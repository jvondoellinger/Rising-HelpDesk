package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateTicketCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class CreateTicketCommandService implements CreateTicketCommandHandler {
	private final TicketRepository repository;
	private final QueueRepository queueRepository;
	private final CurrentUserService currentUserService;

	@Override
	public ResultTransformerStep<Void> handle(CreateTicketCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var queueOptional = queueRepository.findById(cmd.queueId());

				   if (queueOptional.isEmpty()){
					   return Result.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
				   }

				   var ticket = new Ticket(
						 cmd.title(),
						 queueOptional.get(),
						 currentUserService.getUserId(),
						 cmd.deadline()
				   );

				   repository.save(ticket);
				   return Result.success();
			   });
	}

	@Override
	public Class<CreateTicketCommand> getCommandType() {
		return CreateTicketCommand.class;
	}
}
