package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.shared.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.shared.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketsByAuthorQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketsByAuthorQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByAuthorService implements FindTicketsByAuthorQueryHandler{
    private final TicketRepository repository;
    private final TicketMapper mapper;

    @Override
    public ResultB<Pagination<TicketDetails>> handle(FindTicketsByAuthorQuery query) {
        return ResultB.create()
                .flatMap(aVoid -> {
                    var tks = repository.findByAuthorId(query.author(),PaginationFilter.of(query.page(), query.size()));

                    var details = tks.items()
                            .stream()
                            .map(mapper::details)
                            .toList();

                    var pagination = Pagination.of(details, tks.page(), tks.totalPages());

                    return ResultB.of(pagination);
                });
    }

    @Override
    public Class<FindTicketsByAuthorQuery> getQueryType() {
        return FindTicketsByAuthorQuery.class;
    }
}
