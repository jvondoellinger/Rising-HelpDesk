package io.github.jvondoellinger.rising_helpdesk.profile.application.queries;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;

import java.time.LocalDateTime;

public record UserProfileDetails(UserProfileId userId,
						   AccessProfileId accessProfile,
						   LocalDateTime createdAt,
						   LocalDateTime updatedAt) {
}
