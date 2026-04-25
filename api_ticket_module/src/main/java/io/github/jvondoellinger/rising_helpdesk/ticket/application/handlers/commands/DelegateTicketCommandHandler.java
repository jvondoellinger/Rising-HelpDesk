package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.DelegateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.CommandHandler;

public interface DelegateTicketCommandHandler extends CommandHandler<DelegateTicketCommand> {
}

