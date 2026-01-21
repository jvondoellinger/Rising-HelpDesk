package com.github.jvondoellinger.agp_protocol.ticket_module.application.services;

import com.github.jvondoellinger.agp_protocol.application_commons.Pagination;
import com.github.jvondoellinger.agp_protocol.shared_kernel.DomainId;
import com.github.jvondoellinger.agp_protocol.shared_kernel.QueryFilter;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.mappers.TicketMapper;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.queries.TicketDetails;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.usecases.TicketQueryUseCases;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Ticket;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketQueryService implements TicketQueryUseCases {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public TicketDetails queryById(DomainId id) {
		Ticket saved = repository.queryById(id);
		return mapper.details(saved);
	}

	@Override
	public Pagination<TicketDetails> queryByPagination(int page, int size) {
		List<Ticket> tickets = repository.query(QueryFilter.of(page, size));
		List<TicketDetails> details = mapper.details(tickets);
		long total = repository.total();

		return new Pagination<>(details, page, size, total);
	}
}
