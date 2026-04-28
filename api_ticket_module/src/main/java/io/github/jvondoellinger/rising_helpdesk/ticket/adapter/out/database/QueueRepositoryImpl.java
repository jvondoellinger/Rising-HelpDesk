package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaQueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.QueueDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.kernel.PaginationFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {
	private final JpaQueueRepository jpaQueueRepository;
	private final QueueDbMapper mapper;

	@Override
	public void save(Queue entity) {
		var dbEntity = mapper.from(entity);

		jpaQueueRepository.save(dbEntity);
	}

	@Override
	public void update(Queue entity) {
		var dbEntity = mapper.from(entity);

		jpaQueueRepository.save(dbEntity);
	}

	@Override
	public void delete(Queue entity) {
		jpaQueueRepository.deleteById(entity.getId());
	}

	@Override
	public Optional<Queue> findById(UUID id) {
		var optional = jpaQueueRepository.findById(id);

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var queue = optional.get();
		return Optional.of(mapper.toQueue(queue));
	}

	@Override
	public boolean existsById(UUID queueId) {
		return jpaQueueRepository.existsById(queueId);
	}

	@Override
	public Pagination<Queue> findByPagination(PaginationFilter filter) {
		var request = PageRequest.of(filter.page(), filter.size());

		var page = jpaQueueRepository.findAll(request);

		var items = page.stream()
			   .map(mapper::toQueue)
			   .toList();
		System.out.println("ITEMS NO REPOSITORIO: %s".formatted(page.getTotalElements()));
		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		return jpaQueueRepository.count();
	}

	@Override
	public boolean existsByArea(String area) {
		return jpaQueueRepository.existsByArea(area);
	}

	@Override
	public Optional<Queue> findByArea(String area) {
		var optional = jpaQueueRepository.findByArea(area);

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var queue = optional.get();
		return Optional.of(mapper.toQueue(queue));
	}

	@Override
	public Optional<Queue> findBySubarea(String subarea) {
		var optional = jpaQueueRepository.findBySubarea(subarea);

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var queue = optional.get();
		return Optional.of(mapper.toQueue(queue));
	}

	@Override
	public Pagination<Queue> findByAuthor(UUID authorId, PaginationFilter filter) {
		var pageable = PageRequest.of(filter.page(), filter.size());
		var page = jpaQueueRepository.findByAuthor(authorId, pageable);
		var items = page.stream()
			   .map(mapper::toQueue)
			   .toList();
		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}
}
