package io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.PaginationFilter;
import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.jpa.JpaInteractionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.mappers.InteractionDbMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Interaction;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Log4j2
public class InteractionRepositoryImpl implements InteractionRepository {
	private final JpaInteractionRepository jpaInteractionRepository;
	private final InteractionDbMapper mapper;

	@Override
	public void save(Interaction entity) {
		log.info("Saving Interaction to the database.");

		var dbEntity = mapper.from(entity);
		jpaInteractionRepository.save(dbEntity);

		log.info("Interaction saved to the database.");
	}

	@Override
	public void update(Interaction entity) {
		log.info("Updating Interaction in the database.");
		var dbEntity = mapper.from(entity);

		jpaInteractionRepository.save(dbEntity);

		log.info("Interaction updated in the database.");
	}

	@Override
	public void delete(Interaction entity) {
		log.info("Deleting Interaction from the database.");

		jpaInteractionRepository.deleteById(entity.getId());

		log.info("Interaction deleted from the database.");
	}

	@Override
	public Optional<Interaction> findById(UUID id) {
		log.info("Retrieving Interaction by id {} from the database.", id);
		var optionalDbEntity = jpaInteractionRepository.findById(id);

		if (optionalDbEntity.isEmpty()) {
			log.info("Interaction not found. id='{}'", id);
			return Optional.empty();
		}

		var interaction = optionalDbEntity.get();
		var optionalEntity = Optional.of(mapper.toInteraction(interaction));

		log.info("Interaction retrieved from the database.");

		return optionalEntity;
	}

	@Override
	public boolean existsById(UUID interactionId) {
		log.info("Checking existence of Interaction by id '{}'", interactionId);

		var exists = jpaInteractionRepository.existsById(interactionId);

		if (exists) {
			log.info("Interaction with id '{}' exists.", interactionId);
		} else {
			log.info("Interaction with id '{}' does not exist.", interactionId);
		}

		return exists;
	}

	@Override
	public Pagination<Interaction> findByPagination(PaginationFilter filter) {
		log.info("Retrieving paginated Interaction from the database.");

		var request = PageRequest.of(filter.page(), filter.size());
		var page = jpaInteractionRepository.findAll(request);
		var items = page.stream()
			   .map(mapper::toInteraction)
			   .toList();

		log.info(
			   "Interaction pagination completed. page={}, size={}, returnedItems={}, totalItems={}",
			   filter.page(),
			   filter.size(),
			   page.getNumberOfElements(),
			   page.getTotalElements()
		);

		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		log.info("Counting total Interaction records in the database.");

		var total  =jpaInteractionRepository.count();
		log.info("Total Interaction records counted: {}", total);
		return total;
	}
}
