package com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;
import com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.application.dtos.PermissionsDTO;

public record UpdateAccessProfileCommand(
	   AccessProfileId id,
	   String name,
	   PermissionsDTO permissions
) {
}
