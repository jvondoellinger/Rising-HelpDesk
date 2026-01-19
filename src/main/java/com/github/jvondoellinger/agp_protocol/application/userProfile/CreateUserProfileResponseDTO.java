package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.id.AccessProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;
import com.github.jvondoellinger.agp_protocol.domain.profile.AccessProfile;

import java.time.LocalDateTime;

public record CreateUserProfileResponseDTO(
	   UserProfileIdDTO id,
	   AccessProfileIdDTO accessProfileId,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
