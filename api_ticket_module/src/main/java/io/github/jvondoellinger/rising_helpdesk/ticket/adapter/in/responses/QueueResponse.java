package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses;

import java.time.LocalDateTime;
import java.util.UUID;

public record QueueResponse(
	   UUID id,
        String area,
        String subarea,
        LocalDateTime createdAt,
        UUID createdBy,
        LocalDateTime updatedAt,
	   UUID lastUpdatedBy
) {
}
