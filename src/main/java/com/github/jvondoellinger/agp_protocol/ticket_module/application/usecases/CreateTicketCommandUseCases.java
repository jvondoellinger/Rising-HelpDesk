package com.github.jvondoellinger.agp_protocol.ticket_module.application.usecases;

import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.commands.CreateTicketCommand;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.queries.TicketDetails;

public interface CreateTicketCommandUseCases extends CommandUseCase<CreateTicketCommand, TicketDetails> {
}
