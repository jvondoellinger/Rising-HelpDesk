package com.github.jvondoellinger.agp_protocol.api_profile.application.commands;

import com.github.jvondoellinger.agp_protocol.shared_kernel.AccessProfileId;

public record DeleteAccessProfileCommand(
	   AccessProfileId accessProfileId
) {
}
