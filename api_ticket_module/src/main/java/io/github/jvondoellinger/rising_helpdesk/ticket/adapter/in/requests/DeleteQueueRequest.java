package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests;

import java.util.UUID;

public record DeleteQueueRequest(
        UUID id,
        UUID userProfileId
) {
}
