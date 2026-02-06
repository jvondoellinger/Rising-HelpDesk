package com.github.jvondoellinger.agp_protocol.api_profile.application.useCases;

import com.github.jvondoellinger.agp_protocol.api_profile.application.commands.UpdateAccessProfileCommand;
import com.github.jvondoellinger.agp_protocol.api_profile.application.queries.AccessProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface UpdateAccessProfileCommandUseCase extends CommandUseCase<UpdateAccessProfileCommand, AccessProfileDetails> {
}
