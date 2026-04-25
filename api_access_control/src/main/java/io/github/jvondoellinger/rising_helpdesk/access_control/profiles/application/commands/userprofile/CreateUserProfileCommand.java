package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;

import java.util.UUID;

public record CreateUserProfileCommand(UUID userId, UUID accessProfileId) implements Command {
}
