package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.create_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.Command;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicket(
	   String title,
	   UUID queueId,
	   LocalDateTime deadline
) implements Command {
}
