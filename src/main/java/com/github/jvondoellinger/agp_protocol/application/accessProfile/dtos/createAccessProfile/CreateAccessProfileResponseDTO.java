package com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.createAccessProfile;

import com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.PermissionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;

import java.time.LocalDateTime;

public record CreateAccessProfileResponseDTO(
	   DomainIdDTO id,
	   String name,
	   PermissionsDTO permissionsDTO,
	   LocalDateTime createdAt,
	   LocalDateTime updatedAt
) {
}
