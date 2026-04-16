package io.github.jvondoellinger.rising_helpdesk.access_control.domain.aggregate;

import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.Permission;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AccessProfile implements AggregateRoot {
	private final UUID id;
	private final String name;
	private final List<Permission> permissions;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public AccessProfile(UUID id, String name, List<Permission> permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccessProfile(String name, List<Permission> permissions) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.permissions = permissions;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}

	public boolean hasPermission(Permission permission) {
		return permissions.stream()
			   .anyMatch(x ->
					 x.getId()
						    .equals(permission.getId())
			   );
	}

	public boolean hasAllPermissions(List<Permission> permissions) {
		// Pega todos os nomes das permissões desse AccessProfile e põe num Set<>
		var currentPermissions = this.permissions
			   .stream()
			   .map(Permission::getId)
			   .collect(Collectors.toSet());

		// Pega todos nomes das permissões do parametro e campara elas com os dá existem
		return permissions.stream()
			   .map(Permission::getId)
			   .allMatch(currentPermissions::contains);
	}

	// !Getters
	public UUID getId() {
		return id;
	}
	public String getName() {
		return name;
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

