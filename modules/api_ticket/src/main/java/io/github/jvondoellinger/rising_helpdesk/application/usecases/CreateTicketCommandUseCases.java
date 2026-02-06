package com.github.jvondoellinger.agp_protocol.api_ticket.application.usecases;

import com.github.jvondoellinger.agp_protocol.shared_kernel.application_commons.CommandUseCase;
import com.github.jvondoellinger.agp_protocol.api_ticket.application.commands.CreateTicketCommand;
import com.github.jvondoellinger.agp_protocol.api_ticket.application.queries.TicketDetails;

public interface CreateTicketCommandUseCases extends CommandUseCase<CreateTicketCommand, TicketDetails> {
}
