package io.github.jvondoellinger.risinghelpdesk.shared.cqrs;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;

public interface CommandHandler<I extends Command> {
	ResultB<Void> handle(I cmd);

	Class<I> getCommandType();
}
