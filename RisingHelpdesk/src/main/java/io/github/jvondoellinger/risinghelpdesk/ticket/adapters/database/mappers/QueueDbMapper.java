package io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.mappers;

import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.entities.QueueDbEntity;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Queue;
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
			   entity.getAuthor(),
			   entity.getCreatedAt(),
			   entity.getUpdatedAt(),
			   entity.getLastUpdatedBy()
		);
	}
}
