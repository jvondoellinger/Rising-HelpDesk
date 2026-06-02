package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.shared.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaMentionRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.MentionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class MentionRepositoryImpl implements MentionRepository {
	private final JpaMentionRepository jpaMentionRepository;

	@Override
	public void save(Mention entity) {
		log.info("Saving Mention to the database.");

		log.info("Mention saved to the database.");
	}

	@Override
	public void update(Mention entity) {
		log.info("Updating Mention in the database.");

		log.info("Mention updated in the database.");
	}

	@Override
	public void delete(Mention entity) {
		log.info("Deleting Mention from the database.");

		log.info("Mention deleted from the database.");
	}

	@Override
	public Optional<Mention> findById(UUID uuid) {
		log.info("Retrieving Mention by id {} from the database.", uuid);

		log.info("Mention not found. id='{}'", uuid);
		return Optional.empty();
	}

	@Override
	public boolean existsById(UUID uuid) {
		log.info("Checking existence of Mention by id '{}'", uuid);

		var exists = false;

		if (exists) {
			log.info("Mention with id '{}' exists.", uuid);
		} else {
			log.info("Mention with id '{}' does not exist.", uuid);
		}

		return exists;
	}

	@Override
	public Pagination<Mention> findByPagination(PaginationFilter filter) {
		log.info("Retrieving paginated Mention from the database.");

		log.info(
			   "Mention pagination completed. page={}, size={}, returnedItems={}, totalItems={}",
			   filter.page(),
			   filter.size(),
			   0,
			   0
		);

		return null;
	}

	@Override
	public long total() {
		log.info("Counting total Mention records in the database.");

		var total  = 0;
		log.info("Total Mention records counted: {}", total);
		return total;
	}
}
