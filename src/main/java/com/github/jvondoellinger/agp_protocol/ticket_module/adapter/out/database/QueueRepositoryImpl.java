package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.mappers.QueueDbMapper;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Queue;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.QueueDbEntity;
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
