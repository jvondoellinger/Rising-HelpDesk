package io.github.jvondoellinger.rising_helpdesk.profile.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Command;

public record DeleteAccessProfileCommand(
	   AccessProfileId accessProfileId
) implements Command {
}
