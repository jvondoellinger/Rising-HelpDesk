package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.id.AccessProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;

import java.time.LocalDateTime;

public record CreateUserProfileResponseDTO(
	   UserProfileIdDTO id,
	   AccessProfileIdDTO accessProfileId,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
