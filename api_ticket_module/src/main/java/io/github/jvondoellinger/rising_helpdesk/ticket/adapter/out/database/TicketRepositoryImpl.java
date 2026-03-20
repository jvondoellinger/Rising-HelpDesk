package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaTicketRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.TicketDbEntityMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
	private final JpaTicketRepository jpaTicketRepository;
	private final TicketDbEntityMapper mapper;

	@Override
	public void save(Ticket entity) {
		var dbEntity = mapper.from(entity);

		jpaTicketRepository.save(dbEntity);
	}

	@Override
	public void update(Ticket entity) {
		var dbEntity = mapper.from(entity);

		jpaTicketRepository.save(dbEntity);
	}

	@Override
	public void delete(Ticket entity) {
		jpaTicketRepository.deleteById(entity.getId());
	}

	@Override
	public Optional<Ticket> findById(UUID id) {
		var optional = jpaTicketRepository.findById(id);

		if (optional.isEmpty()) {
			return Optional.empty();
		}

		var entity = optional.get();
		return Optional.of(mapper.toTicket(entity));
	}

	@Override
	public boolean existsById(UUID id) {
		return jpaTicketRepository.existsById(id);
	}

	@Override
	public Pagination<Ticket> findByPagination(PaginationFilter filter) {
		var pageable = PageRequest.of(filter.page(), filter.size());
		var page = jpaTicketRepository.findAll(pageable);
		var items = page.stream()
			   .map(mapper::toTicket)
			   .toList();
		return Pagination.of(items, page.getNumber(), page.getTotalPages());
	}

	@Override
	public long total() {
		return jpaTicketRepository.count();
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
