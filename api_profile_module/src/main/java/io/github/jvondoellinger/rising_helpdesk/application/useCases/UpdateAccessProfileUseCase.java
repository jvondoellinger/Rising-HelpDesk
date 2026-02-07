package io.github.jvondoellinger.rising_helpdesk.application.useCases;

import io.github.jvondoellinger.rising_helpdesk.application.commands.UpdateAccessProfileCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.AccessProfileDetails;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandUseCase;

public interface UpdateAccessProfileUseCase extends CommandUseCase<UpdateAccessProfileCommand, AccessProfileDetails> {
}
