package io.github.jvondoellinger.rising_helpdesk.profile.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionMapper {
	public List<Permission> from(PermissionsDTO dto) {
		return dto.values()
			   .stream()
			   .map(Permission::of)
			   .toList();
	}

	public PermissionDetails details(Permission permission) {
		return new PermissionDetails(
			   permission.getId(),
			   permission.getCode(),
			   permission.getCreatedAt()
		);
	}

	public Pagination<PermissionDetails> details(Pagination<Permission> permission) {
		var items = permission.items()
			   .stream()
			   .map(this::details)
			   .toList();
		return Pagination.of(items, permission.page(), permission.totalPages());
	}
}


