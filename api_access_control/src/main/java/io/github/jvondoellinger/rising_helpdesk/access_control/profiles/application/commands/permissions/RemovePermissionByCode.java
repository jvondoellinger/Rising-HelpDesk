package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record RemovePermissionByCode(String code) implements Command {
}
