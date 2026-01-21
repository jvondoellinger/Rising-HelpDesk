package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Queue;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.QueueRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.QueueDbEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueueRepositoryImpl implements QueueRepository {
	private final JpaQueueRepository jpaQueueRepository;

	public QueueRepositoryImpl(JpaQueueRepository jpaQueueRepository) {
		this.jpaQueueRepository = jpaQueueRepository;
	}

	@Override
	public Queue save(Queue entity) {
		return JpaCrudsBridge.save(jpaQueueRepository, new QueueDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public Queue update(Queue entity) {
		return JpaCrudsBridge.save(jpaQueueRepository, new QueueDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(Queue entity) {
		JpaCrudsBridge.delete(jpaQueueRepository, new QueueDbEntity(entity));
	}

	@Override
	public Queue queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaQueueRepository, id.value(), DbEntity::toDomainEntity);

	}

	@Override
	public List<Queue> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaQueueRepository, filter, DbEntity::toDomainEntity);
	}
}
