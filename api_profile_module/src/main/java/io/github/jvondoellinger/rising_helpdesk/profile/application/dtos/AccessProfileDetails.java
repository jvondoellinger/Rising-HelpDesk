package io.github.jvondoellinger.rising_helpdesk.profile.application.dtos;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AccessProfileDetails(
	   UUID accessProfileId,
	   List<Permission> permissions,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
