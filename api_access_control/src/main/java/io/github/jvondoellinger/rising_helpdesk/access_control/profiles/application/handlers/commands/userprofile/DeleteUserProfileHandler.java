package io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.handlers.commands.userprofile;

import io.github.jvondoellinger.rising_helpdesk.access_control.profiles.application.commands.userprofile.DeleteUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.cqrs.CommandHandler;

public interface DeleteUserProfileHandler extends CommandHandler<DeleteUserProfileCommand> {
}
