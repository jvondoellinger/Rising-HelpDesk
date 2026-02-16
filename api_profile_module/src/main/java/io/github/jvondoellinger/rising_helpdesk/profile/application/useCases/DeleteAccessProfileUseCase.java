package io.github.jvondoellinger.rising_helpdesk.profile.application.useCases;

import io.github.jvondoellinger.rising_helpdesk.profile.application.commands.DeleteAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.profile.application.queries.AccessProfileDetails;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface DeleteAccessProfileUseCase extends CommandUseCase<DeleteAccessProfileCommand, AccessProfileDetails> {

}
