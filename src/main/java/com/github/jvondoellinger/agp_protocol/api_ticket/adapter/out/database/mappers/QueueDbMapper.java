package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database.mappers;

import com.github.jvondoellinger.agp_protocol.api_ticket.domain.Queue;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.QueueId;
import com.github.jvondoellinger.agp_protocol.api_ticket.infrastructure.QueueDbEntity;
import org.springframework.stereotype.Service;

@Service
public class QueueDbMapper {
	public QueueDbEntity from(Queue queue) {
		return new QueueDbEntity(queue);
	}

	public Queue toQueue(QueueDbEntity entity) {
		return new Queue(
			   QueueId.of(entity.getDomainId()),
			   entity.getArea(),
			   entity.getSubarea(),
			   entity.getCreatedBy(),
			   entity.getCreatedAt(),
			   entity.getUpdatedAt(),
			   entity.getLastUpdatedBy()
		);
	}
}
