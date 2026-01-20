package com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.updateAccessProfile;


import com.github.jvondoellinger.agp_protocol.application.accessProfile.dtos.PermissionsDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;

public record UpdateAccessProfileRequestDTO(
	   DomainIdDTO id,
	   String name,
	   PermissionsDTO permissions
) {
}
