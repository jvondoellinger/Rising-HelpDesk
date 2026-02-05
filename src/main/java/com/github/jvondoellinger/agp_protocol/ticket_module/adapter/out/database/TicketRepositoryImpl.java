package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database;

import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.infra_commons.JpaCrudsBridge;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.ticket_module.adapter.out.database.mappers.TicketDbEntityMapper;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Ticket;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.TicketRepository;
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
		return JpaCrudsBridge.save(jpaTicketRepository, mapper.from(entity), mapper::toTicket);
	}

	@Override
	public Ticket update(Ticket entity) {
		return JpaCrudsBridge.save(jpaTicketRepository, mapper.from(entity), mapper::toTicket);
	}

	@Override
	public void delete(Ticket entity) {
		JpaCrudsBridge.delete(jpaTicketRepository, mapper.from(entity));
	}

	@Override
	public Ticket queryById(DomainId id) {
		return JpaCrudsBridge.findById(jpaTicketRepository, id.value(), mapper::toTicket);
	}

	@Override
	public List<Ticket> query(QueryFilter filter) {
		return JpaCrudsBridge.findBy(jpaTicketRepository, filter, mapper::toTicket);
	}

	@Override
	public long total() {
		return jpaTicketRepository.count();
	}
}
