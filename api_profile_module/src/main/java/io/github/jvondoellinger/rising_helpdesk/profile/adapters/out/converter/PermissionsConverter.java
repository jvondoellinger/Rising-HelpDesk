package io.github.jvondoellinger.rising_helpdesk.profile.adapters.out.converter;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permission;
import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PermissionsConverter implements AttributeConverter<Permissions, String> {
	@Override
	public String convertToDatabaseColumn(Permissions permissions) {
		return serialize(permissions);
	}

	@Override
	public Permissions convertToEntityAttribute(String s) {
		return deserialize(s);
	}

	public String serialize(Permissions permissions) {
		StringBuilder allPermissions = new StringBuilder();

		for (var p : permissions.readonlyList()) {
			allPermissions.append("%s;".formatted(p.code()));
		}

		return allPermissions.toString();
	}

	public Permissions deserialize(String serialized) {
		if (serialized == null || serialized.isBlank()) return new Permissions();

		String[] values = serialized.split(";");
		Permissions permissions = new Permissions();

		for (var value : values) {
			permissions.add(Permission.of(value));
		}

		return permissions;
	}
}
