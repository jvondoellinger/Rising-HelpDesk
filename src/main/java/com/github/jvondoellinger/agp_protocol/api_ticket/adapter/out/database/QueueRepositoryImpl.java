package com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.api_ticket.adapter.out.database.mappers.QueueDbMapper;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.Queue;
import com.github.jvondoellinger.agp_protocol.api_ticket.domain.QueueRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
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
		return JpaCrudsBridge.save(jpaQueueRepository, mapper.from(entity), mapper::toQueue);
	}

	@Override
	public Queue update(Queue entity) {
		return JpaCrudsBridge.save(jpaQueueRepository, mapper.from(entity), mapper::toQueue);
	}

	@Override
	public void delete(Queue entity) {
		JpaCrudsBridge.delete(jpaQueueRepository, mapper.from(entity));
	}

	@Override
	public Queue queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaQueueRepository, id.value(), mapper::toQueue);
	}

	@Override
	public List<Queue> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaQueueRepository, filter, mapper::toQueue);
	}

	@Override
	public long total() {
		return jpaQueueRepository.count();
	}
}
