package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;

import java.util.function.Function;

public sealed interface CommandResult<O> permits CommandResult.Success, CommandResult.Failure {
	<R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure);

	record Success<O>(O value) implements CommandResult<O> {
		@Override
		public <R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure) {
			return onSuccess.apply(this);
		}
	}

	record Failure<O>(KernelException error) implements CommandResult<O> {
		@Override
		public <R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure) {
			return onFailure.apply(this);
		}
	}
}
