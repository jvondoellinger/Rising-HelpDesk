package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.MentionsResponse;

import java.time.LocalDateTime;

public record CreateTicketRequest(
        String title,
        String queueId,
        MentionsResponse mentions,
        LocalDateTime deadline,
        String openedBy
) {
}
