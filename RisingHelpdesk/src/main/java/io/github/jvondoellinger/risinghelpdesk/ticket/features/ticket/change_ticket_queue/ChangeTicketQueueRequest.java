package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.change_ticket_queue;

import java.util.UUID;

public record ChangeTicketQueueRequest(
	   UUID ticketId,
	   UUID queueId
) {
}
