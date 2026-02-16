package io.github.jvondoellinger.rising_helpdesk.ticket.application.services;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.CreateQueueCommandUseCase;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateQueueCommandService implements CreateQueueCommandUseCase {
	private final QueueRepository repository;

	@Override
	public QueueDetails execute(CreateQueueCommand request) {
		return null;
	}
}
