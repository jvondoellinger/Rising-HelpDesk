package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.abstraction;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.impl.DomainError;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.impl.ResultImpl;

public interface Result<T> {
	static <T> Result<T> error(String code, String description) {
		var error = new DomainError(code, description);
		return new ResultImpl<>(null, error)
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
