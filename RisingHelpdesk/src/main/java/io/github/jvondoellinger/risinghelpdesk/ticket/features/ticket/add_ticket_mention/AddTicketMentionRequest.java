package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_ticket_mention;

import java.util.UUID;

public record AddTicketMentionRequest(
	   UUID userId,
	   UUID ticketId
) {
}
