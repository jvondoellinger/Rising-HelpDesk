package com.github.jvondoellinger.agp_protocol.profile_module.domain;

import com.github.jvondoellinger.agp_protocol.profile_module.domain.valueObjects.Permissions;
import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;

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

