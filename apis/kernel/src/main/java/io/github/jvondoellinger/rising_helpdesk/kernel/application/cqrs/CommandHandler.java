package io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.short_circuiting.ResultB;

public interface CommandHandler<I extends Command> {
	ResultB<Void> handle(I cmd);

	Class<I> getCommandType();
}
