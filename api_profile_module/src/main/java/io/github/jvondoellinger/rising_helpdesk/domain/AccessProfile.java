package io.github.jvondoellinger.rising_helpdesk.domain;

import io.github.jvondoellinger.rising_helpdesk.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

import java.time.LocalDateTime;

public class AccessProfile {
	private final AccessProfileId id;
	private final String name;
	private final Permissions permissions;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public AccessProfile(AccessProfileId id, String name, Permissions permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccessProfile(String name, Permissions permissions) {
		this.id = new AccessProfileId();
		this.name = name;
		this.permissions = permissions;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	public AccessProfileId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}

