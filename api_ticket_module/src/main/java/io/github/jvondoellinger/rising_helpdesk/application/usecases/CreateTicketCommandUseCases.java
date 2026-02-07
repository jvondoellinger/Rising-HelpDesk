package io.github.jvondoellinger.rising_helpdesk.application.usecases;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateTicketCommandUseCases extends CommandUseCase<CreateTicketCommand, TicketDetails> {
}
