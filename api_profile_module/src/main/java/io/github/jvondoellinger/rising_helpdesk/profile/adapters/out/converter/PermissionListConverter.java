package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.converter;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter
public class PermissionListConverter implements AttributeConverter<List<Permission>, String> {
	private final static String DIVISION_SIMBOL = "&";
	@Override
	public String convertToDatabaseColumn(List<Permission> permissions) {
		return serialize(permissions);
	}

	@Override
	public List<Permission> convertToEntityAttribute(String s) {
		return deserialize(s);
	}

	public String serialize(List<Permission> permissions) {
		StringBuilder allPermissions = new StringBuilder();

		for (var p : permissions) {
			allPermissions.append("%s%s".formatted(p.code(), DIVISION_SIMBOL));
		}

		return allPermissions.toString();
	}

	public List<Permission> deserialize(String serialized) {
		if (serialized == null || serialized.isBlank())
			return new ArrayList<>();

		String[] values = serialized.split(DIVISION_SIMBOL);
		List<Permission> permissions = new ArrayList<>();

		for (var value : values) {
			permissions.add(Permission.of(value));
		}

		return permissions;
	}
}
