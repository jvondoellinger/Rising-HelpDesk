package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaTicketRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.TicketDbEntityMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.shared.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
@Log4j2
public class TicketRepositoryImpl implements TicketRepository {
	private final JpaTicketRepository jpaTicketRepository;
	private final TicketDbEntityMapper mapper;

	@Override
	public void save(Ticket entity) {
		log.info("Saving Ticket to the database.");

		var dbEntity = mapper.from(entity);

		jpaTicketRepository.save(dbEntity);

		log.info("Ticket saved to the database.");
	}

	@Override
	public void update(Ticket entity) {
		log.info("Updating Ticket in the database.");
		var dbEntity = mapper.from(entity);

		jpaTicketRepository.save(dbEntity);

		log.info("Ticket updated in the database.");
	}

	@Override
	public void delete(Ticket entity) {
		log.info("Deleting Ticket from the database.");

		jpaTicketRepository.deleteById(entity.getId());

		log.info("Ticket deleted from the database.");
	}

	@Override
	public Optional<Ticket> findById(UUID id) {
		log.info("Retrieving Ticket by id {} from the database.", id);
		var optional = jpaTicketRepository.findById(id);

		if (optional.isEmpty()) {
			log.info("Ticket not found. id='{}'", id);
			return Optional.empty();
		}

		var entity = optional.get();
		log.info("Ticket retrieved from the database.");
		return Optional.of(mapper.toTicket(entity));
	}

	@Override
	public boolean existsById(UUID id) {
		log.info("Checking existence of Ticket by id '{}'", id);

		var exists = jpaTicketRepository.existsById(id);

		if (exists) {
			log.info("Ticket with id '{}' exists.", id);
		} else {
			log.info("Ticket with id '{}' does not exist.", id);
		}

		return exists;
	}

	@Override
	public Pagination<Ticket> findByPagination(PaginationFilter filter) {
		log.info("Retrieving paginated Ticket from the database.");

		var pageable = PageRequest.of(filter.page(), filter.size());


		var page = jpaTicketRepository.findAll(pageable);
		var items = page.stream()
			   .map(mapper::toTicket)
			   .toList();

		log.info(
			   "Ticket pagination completed. page={}, size={}, returnedItems={}, totalItems={}",
			   filter.page(),
			   filter.size(),
			   page.getNumberOfElements(),
			   page.getTotalElements()
		);

		return Pagination.of(items, page.getNumber(), page.getTotalPages());

	}

	@Override
	public long total() {
		log.info("Counting total Ticket records in the database.");

		var total  =jpaTicketRepository.count();
		log.info("Total Ticket records counted: {}", total);
		return total;
	}

	@Override
	public Optional<Ticket> findByNumber(TicketNumber number) {
		if (number == null)
			return Optional.empty();

		var optional = jpaTicketRepository.findByNumber(number.toString());

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var ticket = optional.get();

		return Optional.of(mapper.toTicket(ticket));
	}

	@Override
	public Pagination<Ticket> findByAuthorId(UUID tenantId, PaginationFilter filter) {
		var request = PageRequest.of(filter.page(), filter.size());
		var page = jpaTicketRepository.findByOpenedBy(tenantId, request);
		var items = page.stream()
			   .map(mapper::toTicket)
			   .toList();

		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}
}
