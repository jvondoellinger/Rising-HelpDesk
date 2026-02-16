package io.github.jvondoellinger.rising_helpdesk.profile.application.useCases;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateAccessProfileUseCase extends CommandUseCase<CreateAccessProfileCommand, AccessProfileDetails> {
}
