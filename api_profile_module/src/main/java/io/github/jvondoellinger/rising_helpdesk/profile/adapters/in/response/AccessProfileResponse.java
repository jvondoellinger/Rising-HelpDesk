package io.github.jvondoellinger.rising_helpdesk.profile.adapters.in.response;

import java.time.LocalDateTime;

public record AccessProfileResponse(String accessProfile,
							 LocalDateTime createdAt,
							 LocalDateTime updatedAt) {
}
