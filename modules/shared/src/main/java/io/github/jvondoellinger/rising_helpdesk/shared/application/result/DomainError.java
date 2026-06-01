package io.github.jvondoellinger.rising_helpdesk.shared.application.result;

public record DomainError(String code, String description) {
	public static final DomainError NONE = new DomainError("","");

	@Override
	public String toString() {
		return new StringBuilder()
			   .append("CODE: ")
			   .append(code)
			   .append(" - ")
			   .append("DESCRIPTION: ")
			   .append(description)
			   .toString();
	}
}
