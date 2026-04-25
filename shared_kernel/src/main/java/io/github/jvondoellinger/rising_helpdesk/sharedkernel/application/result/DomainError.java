package io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.impl;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.abstraction.ErrorResult;

public record DomainError(String code, String description) implements ErrorResult {
	// public static final DomainError NONE = new DomainError("","");
}
