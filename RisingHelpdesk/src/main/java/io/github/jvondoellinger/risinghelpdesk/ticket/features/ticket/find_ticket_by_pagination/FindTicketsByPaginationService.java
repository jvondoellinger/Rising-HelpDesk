package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_pagination;

import io.github.jvondoellinger.risinghelpdesk.shared.repository.Pagination;
import io.github.jvondoellinger.risinghelpdesk.shared.repository.PaginationFilter;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketsByPaginationService implements FindTicketsByPaginationHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	@Cacheable(value = "queue-by-pagination", key = "#query.page() + ':' + #query.size()")
	public ResultB<Pagination<TicketDetails>> handle(FindTicketsByPagination query) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var filter = PaginationFilter.of(query.page(), query.size());

				   var pagination = repository.findByPagination(filter);
				   var details = mapper.detailsPagination(pagination);

				   return ResultB.of(details);
			   });
	}

	@Override
	public Class<FindTicketsByPagination> getQueryType() {
		return FindTicketsByPagination.class;
	}
}

