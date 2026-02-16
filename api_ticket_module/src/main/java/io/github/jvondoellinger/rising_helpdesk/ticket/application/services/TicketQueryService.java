package io.github.jvondoellinger.rising_helpdesk.ticket.application.services;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.TicketQueryUseCases;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketRepository;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.DomainId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.Pagination;
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
