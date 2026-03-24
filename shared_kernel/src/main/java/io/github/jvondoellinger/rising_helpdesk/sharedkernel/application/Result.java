package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;

import java.util.Optional;
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

	default boolean isFailure() {
		return this.getClass() == Result.Failure.class;
	}
	static <O> Success<O> success() {
		return new Success<>(null);
	}
	static <O> Success<O> success(O value) {
		return new Success<>(value);
	}

	static <O> Failure<O> failure() {
		return new Failure<>(null);
	}
	static <O> Failure<O> failure(KernelException exception) {
		return new Failure<>(exception);
	}
}
