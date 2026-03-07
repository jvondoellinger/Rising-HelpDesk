package io.github.jvondoellinger.rising_helpdesk.profile.domain.aggregate;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AccessProfile implements AggregateRoot {
	private final AccessProfileId id;
	private final String name;
	private final List<Permission> permissions;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public AccessProfile(AccessProfileId id, String name, List<Permission> permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccessProfile(String name, List<Permission> permissions) {
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

	public boolean hasPermission(Permission permission) {
		return permissions.stream()
				.anyMatch(x ->
						x.id()
						.equals(permission.id())
				);
	}

	public boolean hasAllPermissions(List<Permission> permissions) {
		// Pega todos os nomes das permissões desse AccessProfile e põe num Set<>
		var currentPermissions = this.permissions
				.stream()
				.map(Permission::id).collect(Collectors.toSet());

		// Pega todos nomes das permissões do parametro e campara elas com os dá existem
		return permissions.stream()
				.map(Permission::id)
				.allMatch(currentPermissions::contains);
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}

