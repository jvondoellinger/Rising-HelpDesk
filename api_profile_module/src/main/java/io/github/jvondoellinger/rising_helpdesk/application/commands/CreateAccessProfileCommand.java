package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.domain.valueObjects.Permissions;

public record CreateAccessProfileCommand(
	   String name,
	   Permissions permissions
) {
}
