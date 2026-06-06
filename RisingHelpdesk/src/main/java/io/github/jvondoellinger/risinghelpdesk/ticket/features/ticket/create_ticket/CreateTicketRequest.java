package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.create_ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicketRequest(
	   String title,
	   UUID queueId,
	   LocalDateTime deadline
) {
}
