package com.github.jvondoellinger.agp_protocol.api_profile.application.useCases;

import com.github.jvondoellinger.agp_protocol.api_profile.application.commands.CreateAccessProfileCommand;
import com.github.jvondoellinger.agp_protocol.api_profile.application.queries.AccessProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface CreateAccessProfileCommandUseCase extends CommandUseCase<CreateAccessProfileCommand, AccessProfileDetails> {
}
