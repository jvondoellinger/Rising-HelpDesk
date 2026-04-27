package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;

public interface CommandHandler<I extends Command> {
	ResultTransformerStep<Void> handle(I cmd);

	Class<I> getCommandType();
}
