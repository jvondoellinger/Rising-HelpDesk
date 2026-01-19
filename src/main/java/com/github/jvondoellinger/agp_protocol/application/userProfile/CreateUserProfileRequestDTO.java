package com.github.jvondoellinger.agp_protocol.application.userProfile;

import com.github.jvondoellinger.agp_protocol.application.shared.id.AccessProfileIdDTO;

public record CreateUserProfileRequestDTO(
	   AccessProfileIdDTO accessProfileId
) {
}
