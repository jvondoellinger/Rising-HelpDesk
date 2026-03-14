package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses;

import java.time.LocalDateTime;

public record QueueResponse(
        String domainId,
        String area,
        String subarea,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String lastUpdatedBy
) {
}
