package com.github.jvondoellinger.agp_protocol.profile_module.application.useCases;

import com.github.jvondoellinger.agp_protocol.profile_module.application.commands.CreateAccessProfileCommand;
import com.github.jvondoellinger.agp_protocol.profile_module.application.queries.AccessProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface CreateAccessProfileCommandUseCase extends CommandUseCase<CreateAccessProfileCommand, AccessProfileDetails> {
}
