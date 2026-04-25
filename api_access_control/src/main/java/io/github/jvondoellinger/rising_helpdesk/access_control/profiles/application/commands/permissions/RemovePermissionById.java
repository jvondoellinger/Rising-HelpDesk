package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.cqrs.Command;

import java.util.UUID;

public record RemovePermissionById(UUID id) implements Command {
}
