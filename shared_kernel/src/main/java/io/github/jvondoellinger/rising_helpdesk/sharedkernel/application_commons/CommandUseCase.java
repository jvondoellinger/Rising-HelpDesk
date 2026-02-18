package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

public interface CommandUseCase<I extends Command, O> {
	CommandResult<O> execute(I request);
}
