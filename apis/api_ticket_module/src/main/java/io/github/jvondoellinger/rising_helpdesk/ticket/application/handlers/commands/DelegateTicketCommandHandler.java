package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.DelegateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;

public interface DelegateTicketCommandHandler extends CommandHandler<DelegateTicketCommand> {
}

