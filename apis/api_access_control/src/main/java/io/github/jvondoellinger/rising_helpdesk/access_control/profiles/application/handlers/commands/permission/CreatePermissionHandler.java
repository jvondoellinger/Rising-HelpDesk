package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.permission;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.permissions.CreatePermissionCommand;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;

public interface CreatePermissionHandler extends CommandHandler<CreatePermissionCommand> {
}
