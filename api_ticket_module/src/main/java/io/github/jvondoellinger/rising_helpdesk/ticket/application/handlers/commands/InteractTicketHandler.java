package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddInteractionCommand;

public interface InteractTicketHandler extends CommandHandler<AddInteractionCommand> {
}
