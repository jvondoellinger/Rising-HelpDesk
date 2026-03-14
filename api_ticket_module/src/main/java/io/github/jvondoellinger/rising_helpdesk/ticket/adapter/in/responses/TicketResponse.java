package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses;

import java.time.LocalDateTime;

public record TicketResponse(
        String ticketNumber,
        String title,
        String queueId,
        MentionsResponse mentions,
        LocalDateTime deadline,
        String openedBy,
        LocalDateTime openedOn,
        String lastUpdatedBy,
        LocalDateTime lastUpdatedOn
) {
}
