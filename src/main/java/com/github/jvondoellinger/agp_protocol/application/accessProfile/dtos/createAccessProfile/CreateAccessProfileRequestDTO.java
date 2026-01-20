package com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.createAccessProfile;


import com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.PermissionsDTO;

public record CreateAccessProfileRequestDTO(
	   String name,
	   PermissionsDTO permissions
) {
}
