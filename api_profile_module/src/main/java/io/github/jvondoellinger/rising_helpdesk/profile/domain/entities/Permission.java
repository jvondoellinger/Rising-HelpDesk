package io.github.jvondoellinger.rising_helpdesk.profile.domain.entities;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.anotationTest.FixAfter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Permission {
	private final UUID id;
	private final String code;
	private final LocalDateTime createdAt;

	private Permission(String code) {
        this.id = UUID.randomUUID();
		this.code = code;
		this.createdAt = LocalDateTime.now();
	}

	public Permission(UUID id, String code, LocalDateTime createdAt) {
		this.id = id;
		this.code = code;
		this.createdAt = createdAt;
	}

	@FixAfter
	public static Permission of(String code) {
		if (code == null) {
			throw new RuntimeException("Code is null.");
		}
		else if (code.isBlank()) {
			throw new RuntimeException("Code is blank.");
		}
		else if (!code.contains("::")) {
			throw new RuntimeException("Invalid format.");
		}

		return new Permission(code.toUpperCase());
	}

	public UUID getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;

		Permission that = (Permission) o;
		return Objects.equals(code, that.code);
	}
}
