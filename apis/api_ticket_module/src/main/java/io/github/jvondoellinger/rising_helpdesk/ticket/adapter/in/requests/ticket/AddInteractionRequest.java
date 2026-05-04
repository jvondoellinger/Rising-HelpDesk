package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket;

import java.util.UUID;

public record AddInteractionRequest(
	   String interaction,
	   UUID ticketId
) {
}
