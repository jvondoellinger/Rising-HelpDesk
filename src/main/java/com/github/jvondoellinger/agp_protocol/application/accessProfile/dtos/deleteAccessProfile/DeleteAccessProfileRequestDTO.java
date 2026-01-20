package com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.deleteAccessProfile;


import com.github.jvondoellinger.agp_protocol.application.shared.id.AccessProfileIdDTO;

public record DeleteAccessProfileRequestDTO(
	   AccessProfileIdDTO accessProfileId
) {
}
