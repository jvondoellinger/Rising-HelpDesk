package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_access_profile")
@Getter
@Setter
public class AccessProfileDbEntity {
	@Id
	private UUID id;

	@Column(unique = true)
	private String name;

	// Gera o DDL que cria uma tabela relacionando accessprofile com permission
	@ManyToMany
	@JoinTable(
			name = "tb_access_profile_permissions",
			joinColumns = @JoinColumn(name = "access_profile_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private List<PermissionDbEntity> permissions;

	@OneToMany(mappedBy = "accessProfile")
	private List<UserProfileDbEntity> userProfileDbEntities;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime updatedAt;

	@PersistenceCreator
	public AccessProfileDbEntity(UUID id, String name, List<PermissionDbEntity> permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public AccessProfileDbEntity() {
	}

	public void addPermission(PermissionDbEntity permissionDbEntity) {
		if (permissions == null) {
			permissions = new ArrayList<>();
		}
		permissions.add(permissionDbEntity);
	}
}
