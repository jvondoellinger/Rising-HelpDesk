package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

public record UpdateAccessProfileCommand(
	   AccessProfileId id,
	   String name,
	   PermissionsDTO permissions
) {
}
