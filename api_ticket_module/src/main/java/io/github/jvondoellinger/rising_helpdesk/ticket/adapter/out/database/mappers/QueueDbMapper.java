package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.QueueDbEntity;
import org.springframework.stereotype.Service;

@Service
public class QueueDbMapper {
	public QueueDbEntity from(Queue queue) {
		return new QueueDbEntity(queue);
	}

	public Queue toQueue(QueueDbEntity entity) {
		return new Queue(
			   entity.getId(),
			   entity.getArea(),
			   entity.getSubarea(),
			   entity.getCreatedBy(),
			   entity.getCreatedAt(),
			   entity.getUpdatedAt(),
			   entity.getLastUpdatedBy()
		);
	}
}
