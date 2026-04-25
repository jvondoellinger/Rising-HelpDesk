package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result;

public interface Result<T> {
	static <T> Result<T> error(DomainError error) {
		return new ResultImpl<>(null, error);
	}
	static <T> Result<T> error(String code, String description) {
		var error = new DomainError(code, description);
		return new ResultImpl<>(null, error);
	}
	static <T> Result<T> success() {
		return new ResultImpl<>(null, null);
	}
	static <T> Result<T> success(T value) {
		return new ResultImpl<>(value, null);
	}

	boolean isError();
	boolean isSuccess();
	boolean hasValue();

	T getValue();
	DomainError getError();
}
