package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserProfileResponse(
	   UUID userId,
	   UUID accessProfile,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
