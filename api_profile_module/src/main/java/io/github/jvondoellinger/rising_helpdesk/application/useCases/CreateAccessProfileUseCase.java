package io.github.jvondoellinger.rising_helpdesk.application.useCases;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.AccessProfileDetails;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateAccessProfileUseCase extends CommandUseCase<CreateAccessProfileCommand, AccessProfileDetails> {
}
