package com.github.jvondoellinger.agp_protocol.accessProfile_module.infrastructure;

import com.github.jvondoellinger.agp_protocol.accessProfile_module.adapters.out.converter.PermissionsConverter;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.accessProfile_module.domain.AccessProfile;
import com.github.jvondoellinger.agp_protocol.accessProfile_module.domain.valueObjects.Permissions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_access_profile")
@Getter
@Setter
public class AccessProfileDbEntity implements DbEntity<AccessProfile> {
	@Id
	private String domainId;

	@Column(unique = true)
	private String name;

	@Convert(converter = PermissionsConverter.class)
	private Permissions permissions;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = true)
	private LocalDateTime updatedAt;

	public AccessProfileDbEntity(AccessProfile profile) {
		this.domainId = profile.getDomainId().value();
		this.name = profile.getName();
		this.permissions = profile.getPermissions();

		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PersistenceCreator
	public AccessProfileDbEntity(String domainId, String name, Permissions permissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.domainId = domainId;
		this.name = name;
		this.permissions = permissions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	protected AccessProfileDbEntity() {}

	@Override
	public AccessProfile toDomainEntity() {
		return new AccessProfile(
			   DomainId.parse(domainId),
			   name,
			   permissions,
			   createdAt,
			   updatedAt
		);
	}

	public static AccessProfileDbEntity foreignKey(String id) {
		var accessProfileDbEntity = new AccessProfileDbEntity();
		accessProfileDbEntity.setDomainId(id);
		return accessProfileDbEntity;
	}
}
