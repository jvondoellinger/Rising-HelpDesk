package com.github.jvondoellinger.agp_protocol.api_profile.application.useCases;

import com.github.jvondoellinger.agp_protocol.api_profile.application.commands.DeleteAccessProfileCommand;
import com.github.jvondoellinger.agp_protocol.api_profile.application.queries.AccessProfileDetails;
import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;

public interface DeleteAccessProfileCommandUseCase extends CommandUseCase<DeleteAccessProfileCommand, AccessProfileDetails> {

}
