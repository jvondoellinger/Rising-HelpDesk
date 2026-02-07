package io.github.jvondoellinger.rising_helpdesk.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

import java.time.LocalDateTime;

public record AccessProfileDetails(
	   AccessProfileId accessProfile,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
