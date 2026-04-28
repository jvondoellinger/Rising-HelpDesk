package io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;

public interface CommandHandler<I extends Command> {
	ResultTransformerStep<Void> handle(I cmd);

	Class<I> getCommandType();
}
