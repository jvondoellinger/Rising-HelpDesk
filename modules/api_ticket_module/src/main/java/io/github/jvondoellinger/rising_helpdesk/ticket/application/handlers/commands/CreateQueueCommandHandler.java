package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.queue.CreateQueueCommand;

import io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs.CommandHandler;

public interface CreateQueueCommandHandler extends CommandHandler<CreateQueueCommand> {
}
