package com.github.jvondoellinger.agp_protocol.application.queue;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;

import java.time.LocalDateTime;

public record CreateQueueResponseDTO(
	   QueueIdDTO id,
	   String area,
	   String subarea,
	   UserProfileIdDTO createdById,
	   LocalDateTime createdAt,
	   UserProfileIdDTO lastUpdatedById,
	   LocalDateTime lastUpdatedAt
) {
}
