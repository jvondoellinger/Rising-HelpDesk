package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;

import java.util.UUID;

public record DeleteAccessProfileCommand(
		UUID accessProfileId
) implements Command {
}
