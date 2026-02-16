package io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;

public class Permission {
	private final String code;

	private Permission(String code) {
		this.code = code;
	}

	@FixAfter
	public static Permission of(String code) {
		if (code == null) {
			throw new RuntimeException("Code is null.");
		}
		if (code.isBlank()) {
			throw new RuntimeException("Code is blank.");
		}

		return new Permission(code.toUpperCase());
	}

	public String code() {
		return code;
	}
}
