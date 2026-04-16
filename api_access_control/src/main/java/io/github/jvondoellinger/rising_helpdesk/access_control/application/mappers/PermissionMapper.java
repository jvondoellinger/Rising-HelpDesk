package io.github.jvondoellinger.rising_helpdesk.access_control.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

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


