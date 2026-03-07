package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.InteractionId;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.InteractionDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
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
		return JpaCrudsBridge2.save(jpaInteractionRepository, mapper.from(entity), mapper::toInteraction);
	}

	@Override
	public Interaction update(Interaction entity) {
		return JpaCrudsBridge2.save(jpaInteractionRepository, mapper.from(entity), mapper::toInteraction);
	}

	@Override
	public void delete(Interaction entity) {
		JpaCrudsBridge2.delete(jpaInteractionRepository, mapper.from(entity));
	}

	@Override
	public Interaction queryById(InteractionId id) {
		return JpaCrudsBridge2.findById(jpaInteractionRepository, id.toString(), mapper::toInteraction);
	}

	@Override
	public boolean existsById(InteractionId interactionId) {
		return jpaInteractionRepository.existsById(interactionId.toString());
	}

	@Override
	public List<Interaction> query(QueryFilter filter) {
		return JpaCrudsBridge2.findBy(jpaInteractionRepository, filter, mapper::toInteraction);
	}

	@Override
	public long total() {
		return jpaInteractionRepository.count();
	}
}
