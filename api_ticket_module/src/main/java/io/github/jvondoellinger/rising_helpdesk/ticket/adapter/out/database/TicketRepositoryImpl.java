package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.jpa.JpaTicketRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.TicketDbEntityMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import io.github.jvondoellinger.rising_helpdesk.ticket.infrastructure.TicketDbEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
	private final JpaTicketRepository jpaTicketRepository;
	private final TicketDbEntityMapper mapper;

	@Override
	public Ticket save(Ticket entity) {
		return JpaCrudsBridge2.save(jpaTicketRepository, mapper.from(entity), mapper::toTicket);
	}

	@Override
	public Ticket update(Ticket entity) {
		return JpaCrudsBridge2.save(jpaTicketRepository, mapper.from(entity), mapper::toTicket);
	}

	@Override
	public void delete(Ticket entity) {
		JpaCrudsBridge2.delete(jpaTicketRepository, mapper.from(entity));
	}

	@Override
	public Ticket queryById(UUID id) {
		return JpaCrudsBridge2.findById(jpaTicketRepository, id.toString(), mapper::toTicket);
	}

	@Override
	public boolean existsById(UUID ticketId) {
		return jpaTicketRepository.existsById(ticketId.toString());
	}

	@Override
	public Pagination<Ticket> query(PaginationFilter filter) {
		return paginationFunc(filter, jpaTicketRepository::findAll);
	}

	@Override
	public long total() {
		return jpaTicketRepository.count();
	}

	@Override
	public Optional<Ticket> findByNumber(TicketNumber number) {
		if (number == null)
			return Optional.empty();

		var entity = jpaTicketRepository.findByNumber(number.toString()).orElse(null);

		return entity == null ?
			   Optional.empty() : Optional.of(mapper.toTicket(entity));
	}

	@Override
	public Pagination<Ticket> findByAuthor(String tenantId, PaginationFilter filter) {
		return paginationFunc(filter,
				pageRequest -> jpaTicketRepository.findByOpenedBy(tenantId, pageRequest));
	}

	private Pagination<Ticket> paginationFunc(PaginationFilter filter, Function<PageRequest, Page<TicketDbEntity>> function) {
		var page = function.apply(PageRequest.of(filter.page(), filter.size()));
		var tickets = page.get()
				.map(mapper::toTicket)
				.toList();

		return new Pagination<>(tickets, page.getNumber(), page.getSize(), page.getTotalPages());
	}
}
