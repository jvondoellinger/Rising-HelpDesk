package com.github.jvondoellinger.agp_protocol.api_ticket.application.usecases;

import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;
import com.github.jvondoellinger.agp_protocol.api_ticket.application.commands.CreateQueueCommand;
import com.github.jvondoellinger.agp_protocol.api_ticket.application.queries.QueueDetails;

public interface CreateQueueCommandUseCase extends CommandUseCase<CreateQueueCommand, QueueDetails> {
}
