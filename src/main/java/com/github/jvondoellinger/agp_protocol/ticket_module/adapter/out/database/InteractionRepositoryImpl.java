package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.mappers.InteractionDbMapper;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.Interaction;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.InteractionRepository;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class InteractionRepositoryImpl implements InteractionRepository {
	private final JpaInteractionRepository jpaInteractionRepository;
	private final InteractionDbMapper mapper;

	@Override
	public Interaction save(Interaction entity) {
		return JpaCrudsBridge.save(jpaInteractionRepository, mapper.from(entity), mapper::toInteraction);
	}

	@Override
	public Interaction update(Interaction entity) {
		return JpaCrudsBridge.save(jpaInteractionRepository, mapper.from(entity), mapper::toInteraction);
	}

	@Override
	public void delete(Interaction entity) {
		JpaCrudsBridge.delete(jpaInteractionRepository, mapper.from(entity));
	}

	@Override
	public Interaction queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaInteractionRepository, id.value(), mapper::toInteraction);
	}

	@Override
	public List<Interaction> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaInteractionRepository, filter, mapper::toInteraction);
	}

	@Override
	public long total() {
		return jpaInteractionRepository.count();
	}
}
