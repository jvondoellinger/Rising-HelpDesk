package io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateTicketCommandUseCases extends CommandUseCase<CreateTicketCommand, TicketDetails> {
}
