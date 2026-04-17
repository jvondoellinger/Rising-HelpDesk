package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.adapters.in.response;

import java.time.LocalDateTime;
import java.util.List;

public record AccessProfileResponse(String accessProfile,
							 List<String> permissions,
							 LocalDateTime createdAt,
							 LocalDateTime updatedAt) {
}
