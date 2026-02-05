package com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.application.commands;

import com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.application.dtos.PermissionsDTO;

public record CreateAccessProfileCommand(
	   String name,
	   PermissionsDTO permissions
) {
}
