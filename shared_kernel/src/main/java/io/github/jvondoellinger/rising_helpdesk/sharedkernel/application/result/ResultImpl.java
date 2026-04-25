package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result;

public record ResultImpl<T>(T value, DomainError error) implements Result<T> {
	@Override
	public boolean isError() {
		return error != null;
	}
	@Override
	public boolean isSuccess() {
		return error == null;
	}
	@Override
	public boolean hasValue() {
		return isSuccess() && value != null;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public DomainError getError() {
		return error;
	}
}
