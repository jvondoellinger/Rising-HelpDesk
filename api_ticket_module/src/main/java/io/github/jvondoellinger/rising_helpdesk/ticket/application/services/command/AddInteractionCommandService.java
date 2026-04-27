package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddInteractionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.InteractTicketHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class AddInteractionCommandService implements InteractTicketHandler {
	private final InteractionRepository repository;
	private final TicketRepository ticketRepository;
	private final CurrentUserService currentUserService;

	@Override
	public ResultTransformerStep<Void> handle(AddInteractionCommand cmd) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   if ( !ticketRepository.existsById(cmd.ticketId()) )
					   return Result.error(new DomainError("NO_TICKETS_WERE_FOUND_TO_ADD_INTERACTION", "No tickets were found to add interaction.."));

				   var interaction = new Interaction(
						 cmd.text(),
						 currentUserService.getUserId(),
						 cmd.ticketId()
				   );

				   repository.save(interaction);

				   return Result.success();
			   });

	}

	@Override
	public Class<AddInteractionCommand> getCommandType() {
		return AddInteractionCommand.class;
	}
}
