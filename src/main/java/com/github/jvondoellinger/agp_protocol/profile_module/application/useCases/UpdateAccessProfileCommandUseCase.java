package com.github.jvondoellinger.agp_protocol.profile_module.application.useCases;

import com.github.jvondoellinger.agp_protocol.profile_module.application.commands.UpdateAccessProfileCommand;
import com.github.jvondoellinger.agp_protocol.profile_module.application.queries.AccessProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface UpdateAccessProfileCommandUseCase extends CommandUseCase<UpdateAccessProfileCommand, AccessProfileDetails> {
}
