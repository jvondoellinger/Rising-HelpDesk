package io.github.jvondoellinger.rising_helpdesk.application.commands;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.AccessProfileId;

public record CreateUserProfileCommand(
	   AccessProfileId accessProfileId
) {
}
