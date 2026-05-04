package io.github.jvondoellinger.rising_helpdesk.access_control.auth.application.tokens.utils;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.domain.entities.Permission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StringPermissionsCodec {
	private static final String delimiter = ";";

	public String encode(List<Permission> permissions) {
		if (permissions == null || permissions.isEmpty()) {
			return "";
		}

		return permissions
			   .stream()
			   .map(Permission::getCode)
			   .collect(Collectors.joining(delimiter));
	}

	/**
	 *
	 * @param encoded
	 * @return Attention: Permissions are provided using the same defined codes. However, in this version, there is no validation at the caching or persistence layer to ensure their existence, nor to synchronize fields such as id or createdAt
	 */
	public List<Permission> decode(String encoded) {
		return Arrays.stream(encoded
			   .split(delimiter))
			   .map(Permission::of)
			   .toList();
	}
}
