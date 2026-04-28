package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.CloseTicketCommand;

public interface CloseTicketHandler extends CommandHandler<CloseTicketCommand> {
}
