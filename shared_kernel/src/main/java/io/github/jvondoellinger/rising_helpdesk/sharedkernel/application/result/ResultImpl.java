package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.impl;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.abstraction.Result;

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
		return isSuccess() && value == null;
	}
}
