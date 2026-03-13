package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.InteractionId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.InteractionDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.InteractionDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

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
	public Pagination<Interaction> query(QueryFilter filter) {
		return paginationFunc(filter, jpaInteractionRepository::findAll);
	}

	@Override
	public long total() {
		return jpaInteractionRepository.count();
	}

	private Pagination<Interaction> paginationFunc(QueryFilter filter, Function<PageRequest, Page<InteractionDbEntity>> function) {
		var page = function.apply(PageRequest.of(filter.page(), filter.size()));
		var interaction = page.get()
				.map(mapper::toInteraction)
				.toList();

		return new Pagination<>(interaction, page.getNumber(), page.getSize(), page.getTotalPages());
	}
}
