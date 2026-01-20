package com.github.jvondoellinger.agp_protocol.application.queue.dtos.deleteQueue;

import com.github.jvondoellinger.agp_protocol.application.shared.id.DomainIdDTO;
import com.github.jvondoellinger.agp_protocol.application.shared.id.QueueIdDTO;

public record DeleteQueueRequestDTO(QueueIdDTO queueId) {
}
