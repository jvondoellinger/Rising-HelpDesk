package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FutureFeature;

// Posteriormente, pode-se ser criado um ErrorType e ser adicionado um Exception para tratamento mais avançado de erros!

// ! Urgente:
// Verificar se tem como 'dinamizar' o Failure, assim reaproveitando o mesmo Failure e não precisando reinstanciar, melhorando o uso de recursos desnecessarios!

@Deprecated
@FutureFeature
@FixAfter
public sealed interface ResultV1<T, E> permits ResultV1.Success, ResultV1.Failure {
	// Results
	record Success<T, E>(T value) implements ResultV1<T, E> {
	}

	record Failure<?, E>(E error) implements ResultV1<?, E> {
	}

	// Static Helpers
	static <T, E> ResultV1<T, E> success(T value) {
		return new Success<>(value);
	}
	static <T, E> ResultV1<T, E> failure(E error) {
		return new Failure<>(error);
	}
	static <T, E> ResultV1<T, E> success() {
		return new Success<>(null);
	}

	@SuppressWarnings("unchecked")
	default <U> ResultV1<U, E> cast() {
		return (ResultV1<U, E>) this;
	}

	// Default
	default boolean isFailure() {
		return this.getClass() == ResultV1.Failure.class;
	}
	default boolean isSuccess() {
		return this.getClass() == ResultV1.Success.class;
	}

	default boolean isVoid() {
		if (this instanceof ResultV1.Success<T, E> s)
			return s.value == null;
		return false;
	}

	default E getError() {
		if (this instanceof ResultV1.Failure<T, E> f) {
			return f.error;
		}
		throw new RuntimeException("No error message has registered.");
	}
	default T getValue() {
		if (this instanceof ResultV1.Success<T, E> s)
			return s.value;
		throw new RuntimeException("This action has no value because an error occurred while attempting to perform this action.");
	}

}
