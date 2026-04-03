package io.github.jvondoellinger.rising_helpdesk.profile.application.commands.userprofile;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.List;
import java.util.UUID;

public record CreateUserProfileCommand(UUID userId, UUID accessProfileId) implements Command {
}
