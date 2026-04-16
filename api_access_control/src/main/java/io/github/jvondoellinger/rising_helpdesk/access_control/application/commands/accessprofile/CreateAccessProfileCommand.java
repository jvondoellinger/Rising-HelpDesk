package io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.List;
import java.util.UUID;

public record CreateAccessProfileCommand(
	   String name,
	   List<UUID> permissions
) implements Command {
}
