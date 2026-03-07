package io.github.jvondoellinger.rising_helpdesk.profile.application.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record RemovePermissionsAccessProfileCommand(
	   AccessProfileId id,
	   PermissionsDTO permissions
) implements Command {
}
