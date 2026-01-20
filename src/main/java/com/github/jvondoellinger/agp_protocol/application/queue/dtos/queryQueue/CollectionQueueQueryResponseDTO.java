package com.github.jvondoellinger.agp_protocol.application.queue.dtos.queryQueue;

import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.UserProfileIdDTO;

import java.time.LocalDateTime;

public record CollectionQueueQueryResponseDTO(
	   QueueIdDTO queueId,
	   String area,
	   String subarea,
	   UserProfileIdDTO createdBy,
	   LocalDateTime createdAt,
	   UserProfileIdDTO updatedAt,
	   LocalDateTime updatedBy
) {
}
