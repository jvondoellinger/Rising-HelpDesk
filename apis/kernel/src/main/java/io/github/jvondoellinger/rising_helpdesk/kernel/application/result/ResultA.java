package io.github.jvondoellinger.rising_helpdesk.kernel.application.result;

public interface ResultA<T> {
	static <T> ResultA<T> error(DomainError error) {
		return new ResultAImpl<>(null, error);
	}
	static <T> ResultA<T> error(String code, String description) {
		var error = new DomainError(code, description);
		return new ResultAImpl<>(null, error);
	}
	static <T> ResultA<T> success() {
		return new ResultAImpl<>(null, null);
	}
	static <T> ResultA<T> success(T value) {
		return new ResultAImpl<>(value, null);
	}

	boolean isError();
	boolean isSuccess();
	boolean hasValue();

	T getValue();
	DomainError getError();

	<T> ResultA<T> castWhenError();
}
