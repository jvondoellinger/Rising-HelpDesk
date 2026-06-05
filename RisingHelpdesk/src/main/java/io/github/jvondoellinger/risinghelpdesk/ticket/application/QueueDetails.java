package io.github.jvondoellinger.risinghelpdesk.ticket.application;

import java.time.LocalDateTime;
import java.util.UUID;

public record QueueDetails(
        UUID id,
        String area,
        String subarea,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
        UUID lastUpdatedBy
) {
}
