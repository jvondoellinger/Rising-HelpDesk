package io.github.jvondoellinger.rising_helpdesk.access_control.adapters.in.dtos;

import java.time.LocalDateTime;

public record AccessProfileResult(
	   String accessProfile,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
