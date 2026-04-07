package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddInteractionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.InteractTicketHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.security.CurrentUserService;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddInteractionService implements InteractTicketHandler {
	private final InteractionRepository repository;
	private final TicketRepository ticketRepository;
	private final CurrentUserService currentUserService;
	@Override
	public Result<Void> handle(AddInteractionCommand cmd) {
		var tkOptional = repository.findById(cmd.ticketId());

		if (tkOptional.isEmpty()) {
			return Result.failure("No ticket found.");
		}

		var interaction = new Interaction(
			   cmd.text(),
			   currentUserService.getUserId(),
			   cmd.ticketId()
		);

		repository.save(interaction);

		return Result.success();
	}

	@Override
	public Class<AddInteractionCommand> getCommandType() {
		return AddInteractionCommand.class;
	}
}
