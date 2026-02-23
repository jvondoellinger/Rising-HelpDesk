package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;

import java.util.function.Function;

public sealed interface Result<O> permits Result.Success, Result.Failure {
	<R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure);

	record Success<O>(O value) implements Result<O> {
		@Override
		public <R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure) {
			return onSuccess.apply(this);
		}
	}

	record Failure<O>(KernelException error) implements Result<O> {
		@Override
		public <R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure) {
			return onFailure.apply(this);
		}
	}
}
