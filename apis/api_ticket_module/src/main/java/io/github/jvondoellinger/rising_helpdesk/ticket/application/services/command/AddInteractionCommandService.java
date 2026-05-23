package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.AddInteractionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.InteractTicketHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class AddInteractionCommandService implements InteractTicketHandler {
	private final InteractionRepository repository;
	private final TicketRepository ticketRepository;
	private final CurrentUserService currentUserService;

	@Override
	public ResultB<Void> handle(AddInteractionCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   if ( !ticketRepository.existsById(cmd.ticketId()) )
					   return ResultB.error(new DomainError("NO_TICKETS_WERE_FOUND_TO_ADD_INTERACTION", "No tickets were found to add interaction.."));

				   var interaction = new Interaction(
						 cmd.text(),
						 currentUserService.getUserId(),
						 cmd.ticketId()
				   );

				   repository.save(interaction);
				   return ResultB.create();
			   });

	}

	@Override
	public Class<AddInteractionCommand> getCommandType() {
		return AddInteractionCommand.class;
	}
}
