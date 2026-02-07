package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

public interface CommandUseCase<I, O> {
	O execute(I request);
}
