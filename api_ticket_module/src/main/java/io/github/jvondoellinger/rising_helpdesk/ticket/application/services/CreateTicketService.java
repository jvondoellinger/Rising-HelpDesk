package io.github.jvondoellinger.rising_helpdesk.ticket.application.services;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandResult;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.CreateTicketCommandUseCases;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketService implements CreateTicketCommandUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public CommandResult<TicketDetails> execute(CreateTicketCommand request) {
		return null;
	}
}
