package com.github.jvondoellinger.agp_protocol.api_profile.application.commands;

import com.github.jvondoellinger.agp_protocol.api_profile.application.dtos.PermissionsDTO;
import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;

public record UpdateAccessProfileCommand(
	   AccessProfileId id,
	   String name,
	   PermissionsDTO permissions
) {
}
