package com.github.jvondoellinger.agp_protocol.profile_module.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;

public record CreateUserProfileCommand(
	   AccessProfileId accessProfileId
) {
}
