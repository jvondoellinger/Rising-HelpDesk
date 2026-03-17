package io.github.jvondoellinger.rising_helpdesk.profile.application.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.domain.entities.Permission;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.List;

public record CreateAccessProfileCommand(
	   String name,
	   List<Permission> permissions
) implements Command {
}
