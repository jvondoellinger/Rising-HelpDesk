package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaInteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.InteractionDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Interaction;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class InteractionRepositoryImpl implements InteractionRepository {
	private final JpaInteractionRepository jpaInteractionRepository;
	private final InteractionDbMapper mapper;

	@Override
	public void save(Interaction entity) {
		var dbEntity = mapper.from(entity);

		jpaInteractionRepository.save(dbEntity);
	}

	@Override
	public void update(Interaction entity) {
		var dbEntity = mapper.from(entity);

		jpaInteractionRepository.save(dbEntity);
	}

	@Override
	public void delete(Interaction entity) {
		jpaInteractionRepository.deleteById(entity.getId());
	}

	@Override
	public Optional<Interaction> findById(UUID id) {
		var optional = jpaInteractionRepository.findById(id);

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var interaction = optional.get();
		return Optional.of(mapper.toInteraction(interaction));
	}

	@Override
	public boolean existsById(UUID interactionId) {
		return jpaInteractionRepository.existsById(interactionId);
	}

	@Override
	public Pagination<Interaction> findByPagination(PaginationFilter filter) {
		var request = PageRequest.of(filter.page(), filter.size());
		var page = jpaInteractionRepository.findAll(request);
		var items = page.stream()
			   .map(mapper::toInteraction)
			   .toList();
		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		return jpaInteractionRepository.count();
	}
}
