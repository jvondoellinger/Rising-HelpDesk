package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.RemoveQueueCommand;

public interface RemoveQueueHandler extends CommandHandler<RemoveQueueCommand> {
}
