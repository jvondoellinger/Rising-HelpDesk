package io.github.jvondoellinger.rising_helpdesk.profile.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionDetails;
import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class PermissionMapper {

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
		return new Pagination<>(items, permission.page(), permission.size(), permission.totalPages());
	}
}


