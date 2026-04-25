package io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.CommandHandler;

public interface CreateQueueCommandHandler extends CommandHandler<CreateQueueCommand> {
}
