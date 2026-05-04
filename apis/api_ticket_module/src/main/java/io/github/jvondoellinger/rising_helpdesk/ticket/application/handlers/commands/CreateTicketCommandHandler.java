package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;

public interface CreateTicketCommandHandler extends CommandHandler<CreateTicketCommand> {
}
