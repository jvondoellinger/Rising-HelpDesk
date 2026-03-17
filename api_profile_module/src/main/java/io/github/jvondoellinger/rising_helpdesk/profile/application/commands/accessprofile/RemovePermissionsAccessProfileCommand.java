package io.github.jvondoellinger.rising_helpdesk.profile.application.commands;

import io.github.jvondoellinger.rising_helpdesk.profile.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

public record RemovePermissionsAccessProfileCommand(
        UUID id,
        PermissionsDTO permissions
) implements Command {
}
