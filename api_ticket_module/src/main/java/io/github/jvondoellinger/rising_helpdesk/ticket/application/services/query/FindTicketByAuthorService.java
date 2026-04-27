package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.PaginationFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
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
    public ResultTransformerStep<Pagination<TicketDetails>> handle(FindTicketsByAuthorQuery query) {
        return ResultTransformerStep.create()
                .flatMap(aVoid -> {
                    var tks = repository.findByAuthorId(query.author(),PaginationFilter.of(query.page(), query.size()));

                    var details = tks.items()
                            .stream()
                            .map(mapper::details)
                            .toList();

                    var pagination = Pagination.of(details, tks.page(), tks.totalPages());

                    return Result.success(pagination);
                });
    }

    @Override
    public Class<FindTicketsByAuthorQuery> getQueryType() {
        return FindTicketsByAuthorQuery.class;
    }
}
