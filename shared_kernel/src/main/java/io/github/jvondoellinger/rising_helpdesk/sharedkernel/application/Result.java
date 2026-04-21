package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FutureFeature;

import java.util.function.Function;

// Posteriormente, pode-se ser criado um ErrorType e ser adicionado um Exception para tratamento mais avançado de erros!

@FutureFeature
public sealed interface Result<O> permits Result.Success, Result.Failure {
	@Deprecated
	<R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure);

	record Success<O>(O value) implements Result<O> {
		@Override
		public <R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure) {
			return onSuccess.apply(this);
		}
	}

	record Failure<O>(String error) implements Result<O> {
		@Override
		public <R> R fold(Function<Success<O>, R> onSuccess, Function<Failure<O>, R> onFailure) {
			return onFailure.apply(this);
		}
	}


	static <O> Success<O> success() {
		return new Success<>(null);
	}
	static <O> Success<O> success(O value) {
		return new Success<>(value);
	}

	default O getValue() {
		if (this instanceof Result.Success<O> s)
			return s.value;
		throw new RuntimeException("This action has no value because an error occurred while attempting to perform this action.");
	}

	static <O> Failure<O> failure() {
		return new Failure<>(null);
	}
	static <O> Failure<O> failure(String error) {
		return new Failure<>(error);
	}
	default boolean isFailure() {
		return this.getClass() == Result.Failure.class;
	}
	default boolean isSuccess() {
		return this.getClass() == Result.Success.class;
	}
	default String getError() {
		if (this instanceof Result.Failure<O> f) {
			return f.error;
		}
		throw new RuntimeException("No error message has registered.");
	}
}
