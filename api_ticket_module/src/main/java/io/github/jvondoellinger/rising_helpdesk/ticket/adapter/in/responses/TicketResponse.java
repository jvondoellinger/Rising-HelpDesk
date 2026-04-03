package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponse(
        String ticketNumber,
        String title,
        QueueResponse queueResponse,
        MentionsResponse mentions,
        LocalDateTime deadline,
        UUID openedBy,
        LocalDateTime openedOn,
        UUID lastUpdatedBy,
        LocalDateTime lastUpdatedOn
) {
}
