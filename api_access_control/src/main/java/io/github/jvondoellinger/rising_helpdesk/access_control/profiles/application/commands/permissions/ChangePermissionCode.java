package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.Command;

public record ChangePermissionCode(String newValue) implements Command {
}
