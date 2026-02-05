package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.mappers;

import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Queue;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueId;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.QueueDbEntity;
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
