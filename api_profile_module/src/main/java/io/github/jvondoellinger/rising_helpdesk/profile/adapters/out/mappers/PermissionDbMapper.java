package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.entities.PermissionDbEntity;
import org.springframework.stereotype.Service;

@Service
public final class PermissionDbMapper {
	public PermissionDbEntity from(Permission permission) {
		return new PermissionDbEntity(
			   permission.getId(),
			   permission.getCode(),
			   	permission.getCreatedAt()
		);
	}

	public Permission toPermission(PermissionDbEntity dbEntity) {
		return new Permission(
			   dbEntity.getId(),
			   dbEntity.getCode(),
			   dbEntity.getCreatedAt()
		);
	}
}
