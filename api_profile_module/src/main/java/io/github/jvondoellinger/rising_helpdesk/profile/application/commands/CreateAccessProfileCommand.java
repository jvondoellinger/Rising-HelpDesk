package io.github.jvondoellinger.rising_helpdesk.profile.application.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.valueObjects.Permissions;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

public record CreateAccessProfileCommand(
	   String name,
	   Permissions permissions
) implements Command {
}
