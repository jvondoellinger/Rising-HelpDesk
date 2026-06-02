package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaQueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.QueueDbMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.PaginationFilter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Log4j2
public class QueueRepositoryImpl implements QueueRepository {
	private final JpaQueueRepository jpaQueueRepository;
	private final QueueDbMapper mapper;

	@Override
	public void save(Queue entity) {
		log.info("Saving Queue to the database.");

		var dbEntity = mapper.from(entity);

		jpaQueueRepository.save(dbEntity);

		log.info("Queue saved to the database.");
	}

	@Override
	public void update(Queue entity) {
		log.info("Updating Queue in the database.");
		var dbEntity = mapper.from(entity);

		jpaQueueRepository.save(dbEntity);

		log.info("Queue updated in the database.");
	}

	@Override
	public void delete(Queue entity) {
		log.info("Deleting Queue from the database.");

		jpaQueueRepository.deleteById(entity.getId());

		log.info("Queue deleted from the database.");
	}

	@Override
	public Optional<Queue> findById(UUID id) {
		log.info("Retrieving Queue by id {} from the database.", id);
		var optional = jpaQueueRepository.findById(id);

		if (optional.isEmpty()) {
			log.info("Queue not found. id='{}'", id);
			return Optional.empty();
		}

		var queue = optional.get();
		log.info("Queue retrieved from the database.");
		return Optional.of(mapper.toQueue(queue));
	}

	@Override
	public boolean existsById(UUID queueId) {
		log.info("Checking existence of Queue by id '{}'", queueId);

		var exists = jpaQueueRepository.existsById(queueId);

		if (exists) {
			log.info("Queue with id '{}' exists.", queueId);
		} else {
			log.info("Queue with id '{}' does not exist.", queueId);
		}

		return exists;
	}

	@Override
	public Pagination<Queue> findByPagination(PaginationFilter filter) {
		log.info("Retrieving paginated Queue from the database.");

		var request = PageRequest.of(filter.page(), filter.size());

		var page = jpaQueueRepository.findAll(request);

		var items = page.stream()
			   .map(mapper::toQueue)
			   .toList();
		System.out.println("ITEMS NO REPOSITORIO: %s".formatted(page.getTotalElements()));

		log.info(
			   "Queue pagination completed. page={}, size={}, returnedItems={}, totalItems={}",
			   filter.page(),
			   filter.size(),
			   page.getNumberOfElements(),
			   page.getTotalElements()
		);

		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		log.info("Counting total Queue records in the database.");

		var total  =jpaQueueRepository.count();
		log.info("Total Queue records counted: {}", total);
		return total;
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
