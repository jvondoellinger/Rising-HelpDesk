package com.github.jvondoellinger.agp_protocol.api_authorization.userProfile_module.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;

public record DeleteAccessProfileCommand(
	   AccessProfileId accessProfileId
) {
}
