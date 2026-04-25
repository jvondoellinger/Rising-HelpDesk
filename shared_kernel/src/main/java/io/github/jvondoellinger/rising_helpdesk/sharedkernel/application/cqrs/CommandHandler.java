package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;

public interface CommandHandler<I extends Command> {
	Result<Void> handle(I cmd);

	Class<I> getCommandType();
}
