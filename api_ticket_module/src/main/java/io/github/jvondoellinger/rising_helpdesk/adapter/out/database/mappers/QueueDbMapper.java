package io.github.jvondoellinger.rising_helpdesk.adapter.out.database.mappers;

import io.github.jvondoellinger.rising_helpdesk.domain.Queue;
import io.github.jvondoellinger.rising_helpdesk.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.infrastructure.QueueDbEntity;
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
