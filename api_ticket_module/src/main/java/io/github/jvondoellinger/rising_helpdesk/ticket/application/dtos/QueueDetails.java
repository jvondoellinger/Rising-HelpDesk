package io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record QueueDetails(
        UUID domainId,
        String area,
        String subarea,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID lastUpdatedBy
) {
}
