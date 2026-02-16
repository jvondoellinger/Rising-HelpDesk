package io.github.jvondoellinger.rising_helpdesk.profile.application.useCases;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.CreateUserProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.UserProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface CreateUserProfileUseCase extends CommandUseCase<CreateUserProfileCommand, UserProfileDetails> {
}
