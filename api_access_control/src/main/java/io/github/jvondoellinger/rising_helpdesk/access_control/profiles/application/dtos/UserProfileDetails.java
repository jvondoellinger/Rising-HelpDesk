package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserProfileDetails(
        UUID userId,
        UUID accessProfile,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
