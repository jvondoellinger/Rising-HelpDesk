package io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.QueueDetails;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateQueueCommandUseCase extends CommandUseCase<CreateQueueCommand, QueueDetails> {
}
