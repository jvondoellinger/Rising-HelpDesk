package io.github.jvondoellinger.rising_helpdesk.kernel.application.result;

public record DomainError(String code, String description) {
	public static final DomainError NONE = new DomainError("","");
}
