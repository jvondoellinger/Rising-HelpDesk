package io.github.jvondoellinger.rising_helpdesk.adapter.out.database;

import io.github.jvondoellinger.rising_helpdesk.adapter.out.database.mappers.TicketDbEntityMapper;
import io.github.jvondoellinger.rising_helpdesk.domain.Ticket;
import io.github.jvondoellinger.rising_helpdesk.domain.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public Ticket queryById(DomainId id) {
		return JpaCrudsBridge2.findById(jpaTicketRepository, id.value(), mapper::toTicket);
	}

	@Override
	public List<Ticket> query(QueryFilter filter) {
		return JpaCrudsBridge2.findBy(jpaTicketRepository, filter, mapper::toTicket);
	}

	@Override
	public long total() {
		return jpaTicketRepository.count();
	}
}
