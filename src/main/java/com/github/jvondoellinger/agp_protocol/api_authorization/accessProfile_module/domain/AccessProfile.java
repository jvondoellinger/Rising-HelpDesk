package com.github.jvondoellinger.agp_protocol.api_authorization.accessProfile_module.domain;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.api_authorization.accessProfile_module.domain.valueObjects.Permissions;

import java.time.LocalDateTime;

public class AccessProfile {
	private final DomainId domainId;
	private final String name;
	private final Permissions permissions;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public AccessProfile(DomainId domainId, String name, Permissions permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.domainId = domainId;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccessProfile(String name, Permissions permissions) {
		this.domainId = DomainId.create();
		this.name = name;
		this.permissions = permissions;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	public DomainId getDomainId() {
		return domainId;
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

