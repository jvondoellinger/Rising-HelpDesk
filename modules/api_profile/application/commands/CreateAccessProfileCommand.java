package com.github.jvondoellinger.agp_protocol.api_profile.application.commands;

import com.github.jvondoellinger.agp_protocol.api_profile.application.dtos.PermissionsDTO;

public record CreateAccessProfileCommand(
	   String name,
	   PermissionsDTO permissions
) {
}
