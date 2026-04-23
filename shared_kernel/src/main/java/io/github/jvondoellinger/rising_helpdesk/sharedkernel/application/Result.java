package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FutureFeature;

// Posteriormente, pode-se ser criado um ErrorType e ser adicionado um Exception para tratamento mais avançado de erros!

// ! Urgente:
// Verificar se tem como 'dinamizar' o Failure, assim reaproveitando o mesmo Failure e não precisando reinstanciar, melhorando o uso de recursos desnecessarios!

@FutureFeature
@FixAfter
public sealed interface Result<T, E> permits Result.Success, Result.Failure {
	// Results
	record Success<T, E>(T value) implements Result<T, E> {
	}

	record Failure<T, E>(E error) implements Result<T, E> {
	}

	// Static Helpers
	static <T, E> Result<T, E> success(T value) {
		return new Success<>(value);
	}
	static <T, E> Result<T, E> failure(E error) {
		return new Failure<>(error);
	}
	static <T, E> Result<T, E> success() {
		return new Success<>(null);
	}

	// Default
	default boolean isFailure() {
		return this.getClass() == Result.Failure.class;
	}
	default boolean isSuccess() {
		return this.getClass() == Result.Success.class;
	}
	default E getError() {
		if (this instanceof Result.Failure<T, E> f) {
			return f.error;
		}
		throw new RuntimeException("No error message has registered.");
	}
	default T getValue() {
		if (this instanceof Result.Success<T, E> s)
			return s.value;
		throw new RuntimeException("This action has no value because an error occurred while attempting to perform this action.");
	}

}
