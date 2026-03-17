package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaQueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.QueueDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.QueueDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.function.Function;

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
	public Queue queryById(UUID id) {
		return JpaCrudsBridge2.findById(jpaQueueRepository, id.toString(), mapper::toQueue);
	}

	@Override
	public boolean existsById(UUID queueId) {
		return jpaQueueRepository.existsById(queueId.toString());
	}

	@Override
	public Pagination<Queue> query(QueryFilter filter) {
		return paginationFunc(filter, jpaQueueRepository::findAll);
	}

	@Override
	public long total() {
		return jpaQueueRepository.count();
	}

	@Override
	public boolean existsByArea(String area) {
		return jpaQueueRepository.existsByArea(area);
	}

	private Pagination<Queue> paginationFunc(QueryFilter filter, Function<PageRequest, Page<QueueDbEntity>> function) {
		var page = function.apply(PageRequest.of(filter.page(), filter.size()));
		var queue = page.get()
				.map(mapper::toQueue)
				.toList();

		return new Pagination<>(queue, page.getNumber(), page.getSize(), page.getTotalPages());
	}
}
