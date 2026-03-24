package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByPaginationQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByPaginationQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByPaginationService implements FindTicketByPaginationQueryHandler {
    private final TicketRepository repository;
    private final TicketMapper mapper;

    @Override
    public Result<Pagination<TicketDetails>> handle(FindTicketByPaginationQuery query) {
        var filter = PaginationFilter.of(query.page(), query.size());
        var pagination = repository.findByPagination(filter);
        var details = mapper.detailsPagination(pagination);

        return new Result.Success<>(details);
    }

    @Override
    public Class<FindTicketByPaginationQuery> getQueryType() {
        return FindTicketByPaginationQuery.class;
    }
}
