package io.github.jvondoellinger.rising_helpdesk.profile.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionMapper {

	public Permissions from(PermissionsDTO dto) {
		var values = dto.values();
		var list = values.stream()
			   .map(Permission::of)
			   .collect(Collectors.toCollection(ArrayList::new));
		return new Permissions(list);
	}
}
