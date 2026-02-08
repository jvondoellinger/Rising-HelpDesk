package io.github.jvondoellinger.rising_helpdesk.application.usecases;

import io.github.jvondoellinger.rising_helpdesk.application.commands.DeleteTicketQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface DelegateTicketUseCases extends CommandUseCase<DeleteTicketQueueCommand, TicketDetails> {
}

