package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.Interaction;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.InteractionRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.DbEntity;
import com.github.jvondoellinger.agp_protocol.ticket_module.infrastructure.InteractionDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class InteractionRepositoryImpl implements InteractionRepository {
	private final JpaInteractionRepository jpaInteractionRepository;

	@Override
	public Interaction save(Interaction entity) {
		return JpaCrudsBridge.save(jpaInteractionRepository, new InteractionDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public Interaction update(Interaction entity) {
		return JpaCrudsBridge.save(jpaInteractionRepository, new InteractionDbEntity(entity), DbEntity::toDomainEntity);
	}

	@Override
	public void delete(Interaction entity) {
		JpaCrudsBridge.delete(jpaInteractionRepository, new InteractionDbEntity(entity));
	}

	@Override
	public Interaction queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaInteractionRepository, id.value(), DbEntity::toDomainEntity);
	}

	@Override
	public List<Interaction> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaInteractionRepository, filter, DbEntity::toDomainEntity);
	}

	@Override
	public long total() {
		return jpaInteractionRepository.count();
	}
}
