package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;

import java.util.List;
import java.util.UUID;

public record CreateAccessProfileCommand(
	   String name,
	   List<UUID> permissions
) implements Command {
}
