package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateTicketCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketCommandService implements CreateTicketCommandHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public Result<Void> handle(CreateTicketCommand cmd) {
		return null;
	}

	@Override
	public Class<CreateTicketCommand> getCommandType() {
		return CreateTicketCommand.class;
	}
}
