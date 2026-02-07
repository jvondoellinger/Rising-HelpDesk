package io.github.jvondoellinger.rising_helpdesk.application.useCases;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateUserProfileUseCase extends CommandUseCase<CreateUserProfileCommand, UserProfileDetails> {
}
