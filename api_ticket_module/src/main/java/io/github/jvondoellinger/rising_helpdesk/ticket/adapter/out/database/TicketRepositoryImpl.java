package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.out.database.mappers.TicketDbEntityMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.valueObjects.TicketNumber;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
	public Ticket queryById(TicketId id) {
		return JpaCrudsBridge2.findById(jpaTicketRepository, id.toString(), mapper::toTicket);
	}

	@Override
	public List<Ticket> query(QueryFilter filter) {
		return JpaCrudsBridge2.findBy(jpaTicketRepository, filter, mapper::toTicket);
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
}
