package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;

public sealed interface CommandResult<O> permits CommandResult.Success, CommandResult.Failure {
	record Success<O>(O value) implements CommandResult<O> {}
	record Failure<O>(KernelException error) implements CommandResult<O> {}
}
