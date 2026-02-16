package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.QueueDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {
	private final JpaQueueRepository jpaQueueRepository;
	private final QueueDbMapper mapper;

	@Override
	public Queue save(Queue entity) {
		return JpaCrudsBridge2.save(jpaQueueRepository, mapper.from(entity), mapper::toQueue);
	}

	@Override
	public Queue update(Queue entity) {
		return JpaCrudsBridge2.save(jpaQueueRepository, mapper.from(entity), mapper::toQueue);
	}

	@Override
	public void delete(Queue entity) {
		JpaCrudsBridge2.delete(jpaQueueRepository, mapper.from(entity));
	}

	@Override
	public Queue queryById(DomainId id) {
		return JpaCrudsBridge2.findById(jpaQueueRepository, id.value(), mapper::toQueue);
	}

	@Override
	public List<Queue> query(QueryFilter filter) {
		return JpaCrudsBridge2.findBy(jpaQueueRepository, filter, mapper::toQueue);
	}

	@Override
	public long total() {
		return jpaQueueRepository.count();
	}
}
