package io.github.jvondoellinger.rising_helpdesk.access_control.repository.application.handlers.helpers;

import io.github.jvondoellinger.rising_helpdesk.access_control.domain.aggregate.AccessProfile;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.access_control.domain.entities.UserProfile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public final class FakeEntityFactory {

	private FakeEntityFactory() {
	}

	public static Permission permission(UUID id, String code) {
		return new Permission(id, code.toUpperCase(), LocalDateTime.now());
	}

	public static AccessProfile accessProfile(UUID id, String name, List<Permission> permissions) {
		return new AccessProfile(id, name, permissions, LocalDateTime.now().minusDays(1), null);
	}

	public static UserProfile userProfile(UUID userId, UUID accessProfileId) {
		return new UserProfile(userId, accessProfileId);
	}
}
