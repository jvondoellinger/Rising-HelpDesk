package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Command;

public record CreateUserProfileCommand(
	   AccessProfileId accessProfileId
) implements Command {
}
