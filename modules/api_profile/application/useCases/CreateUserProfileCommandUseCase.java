package com.github.jvondoellinger.agp_protocol.api_profile.application.useCases;

import com.github.jvondoellinger.agp_protocol.api_profile.application.commands.CreateUserProfileCommand;
import com.github.jvondoellinger.agp_protocol.api_profile.application.queries.UserProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface CreateUserProfileCommandUseCase extends CommandUseCase<CreateUserProfileCommand, UserProfileDetails> {
}
