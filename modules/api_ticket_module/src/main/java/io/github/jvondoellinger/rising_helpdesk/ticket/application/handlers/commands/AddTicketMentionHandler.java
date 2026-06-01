package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.AddTicketMentionCommand;

public interface AddTicketMentionHandler extends CommandHandler<AddTicketMentionCommand> {
}
