package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

public interface CommandHandler<I extends Command> {
	Result<Void, String> handle(I cmd);

	Class<I> getCommandType();
}
