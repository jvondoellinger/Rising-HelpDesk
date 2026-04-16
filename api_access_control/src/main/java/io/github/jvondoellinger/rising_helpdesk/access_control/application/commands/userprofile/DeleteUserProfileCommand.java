package io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.userprofile;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

public record DeleteUserProfileCommand(UUID id) implements Command {
}
