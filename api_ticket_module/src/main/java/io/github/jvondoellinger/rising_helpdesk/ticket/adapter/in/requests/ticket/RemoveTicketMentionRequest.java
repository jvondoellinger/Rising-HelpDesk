package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket;

import java.util.UUID;

public record RemoveTicketMentionRequest(
	   UUID userId,
	   UUID ticketId
) {
}
