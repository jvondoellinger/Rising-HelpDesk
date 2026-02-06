package com.github.jvondoellinger.agp_protocol.api_profile.application.queries;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.UserProfileId;
import java.time.LocalDateTime;

public record UserProfileDetails(UserProfileId userId,
						   AccessProfileId accessProfile,
						   LocalDateTime createdAt,
						   LocalDateTime updatedAt) {
}
