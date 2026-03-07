package io.github.jvondoellinger.rising_helpdesk.profile.application.queries;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

import java.time.LocalDateTime;
import java.util.List;

public record AccessProfileDetails(
	   AccessProfileId accessProfileId,
	   List<Permission> permissions,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
