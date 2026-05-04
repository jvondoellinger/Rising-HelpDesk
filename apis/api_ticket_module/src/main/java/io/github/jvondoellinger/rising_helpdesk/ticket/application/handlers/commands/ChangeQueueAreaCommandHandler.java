package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.ChangeQueueAreaCommand;

public interface ChangeQueueAreaCommandHandler extends CommandHandler<ChangeQueueAreaCommand> {
}
