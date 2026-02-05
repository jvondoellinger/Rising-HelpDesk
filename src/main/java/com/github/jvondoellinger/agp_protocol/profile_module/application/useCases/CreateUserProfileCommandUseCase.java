package com.github.jvondoellinger.agp_protocol.profile_module.application.useCases;

import com.github.jvondoellinger.agp_protocol.profile_module.application.commands.CreateUserProfileCommand;
import com.github.jvondoellinger.agp_protocol.profile_module.application.queries.UserProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface CreateUserProfileCommandUseCase extends CommandUseCase<CreateUserProfileCommand, UserProfileDetails> {
}
