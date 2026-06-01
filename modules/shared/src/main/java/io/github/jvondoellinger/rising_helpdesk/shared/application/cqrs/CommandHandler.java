package io.github.jvondoellinger.rising_helpdesk.shared.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;

public interface CommandHandler<I extends Command> {
	ResultB<Void> handle(I cmd);

	Class<I> getCommandType();
}
