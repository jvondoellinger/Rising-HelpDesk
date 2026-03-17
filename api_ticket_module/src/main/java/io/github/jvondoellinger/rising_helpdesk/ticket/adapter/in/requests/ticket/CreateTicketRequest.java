package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicketRequest(
        String title,
        UUID queueId,
        LocalDateTime deadline,
        UUID openedBy
) {
}
