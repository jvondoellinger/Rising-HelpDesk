package io.github.jvondoellinger.rising_helpdesk.profile.application.queries;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

import java.time.LocalDateTime;

public record AccessProfileDetails(
	   AccessProfileId accessProfileId,
	   Permissions permissions,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
