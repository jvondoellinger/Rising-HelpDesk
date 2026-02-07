package io.github.jvondoellinger.rising_helpdesk.application.services;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.QueueDetails;
import io.github.jvondoellinger.rising_helpdesk.application.usecases.CreateQueueCommandUseCase;
import io.github.jvondoellinger.rising_helpdesk.domain.QueueRepository;
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
