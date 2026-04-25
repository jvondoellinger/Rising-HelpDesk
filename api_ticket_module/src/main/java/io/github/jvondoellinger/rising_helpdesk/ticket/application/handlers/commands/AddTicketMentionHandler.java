package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddTicketMentionCommand;

public interface AddTicketMentionHandler extends CommandHandler<AddTicketMentionCommand> {
}
