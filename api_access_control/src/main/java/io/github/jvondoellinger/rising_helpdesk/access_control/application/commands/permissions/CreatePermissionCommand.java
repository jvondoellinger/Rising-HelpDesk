package io.github.jvondoellinger.rising_helpdesk.access_control.application.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record CreatePermissionCommand(String command) implements Command {
}
