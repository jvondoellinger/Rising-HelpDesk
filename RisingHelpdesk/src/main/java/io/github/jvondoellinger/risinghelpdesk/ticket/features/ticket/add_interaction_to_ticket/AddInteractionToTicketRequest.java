package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_to_ticket;

import java.util.UUID;

public record AddInteractionToTicketRequest(
	   String text,
	   UUID ticketId
) {
}
