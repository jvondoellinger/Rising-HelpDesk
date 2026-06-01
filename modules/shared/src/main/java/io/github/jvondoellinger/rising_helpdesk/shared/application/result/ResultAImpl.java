package io.github.jvondoellinger.rising_helpdesk.shared.application.result;

public record ResultAImpl<T>(T value, DomainError error) implements ResultA<T> {
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
	
	@Override
	public <T1> ResultA<T1> castWhenError() {
		return new ResultAImpl<>(null, error);
	}
}
