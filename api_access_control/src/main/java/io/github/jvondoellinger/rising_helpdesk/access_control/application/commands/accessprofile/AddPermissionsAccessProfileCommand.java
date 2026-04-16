package io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.accessprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.application.dtos.PermissionsDTO;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

import java.util.UUID;

public record AddPermissionsAccessProfileCommand(
        UUID id,
        PermissionsDTO permissions
) implements Command {
}
