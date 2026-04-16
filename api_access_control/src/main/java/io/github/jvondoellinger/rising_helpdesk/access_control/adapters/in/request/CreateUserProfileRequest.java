package io.github.jvondoellinger.rising_helpdesk.access_control.adapters.in.request;

import java.util.UUID;

public record CreateUserProfileRequest(UUID userId, UUID accessProfileId) {
}
