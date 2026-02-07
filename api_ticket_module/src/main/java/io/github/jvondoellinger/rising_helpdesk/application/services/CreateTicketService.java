package io.github.jvondoellinger.rising_helpdesk.application.services;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.application.usecases.CreateTicketCommandUseCases;
import io.github.jvondoellinger.rising_helpdesk.domain.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketService implements CreateTicketCommandUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public TicketDetails execute(CreateTicketCommand request) {
		return null;
	}
}
