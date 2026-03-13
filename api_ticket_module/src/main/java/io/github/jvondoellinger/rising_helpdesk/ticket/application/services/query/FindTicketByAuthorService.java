package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.QueryFilter;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketsByAuthorQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketsByAuthorQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByAuthorService implements FindTicketsByAuthorQueryHandler{
    private final TicketRepository repository;
    private final TicketMapper mapper;

    @Override
    public Result<Pagination<TicketDetails>> handle(FindTicketsByAuthorQuery query) {
        var tks = repository.findByAuthor(query.author(), QueryFilter.of(query.size(), query.page()));
        var details = tks.items().stream().map(mapper::details).toList();
        var pagination = new Pagination<>(details, tks.page(), tks.size(), tks.totalPages());

        return new Result.Success<>(pagination);
    }

    @Override
    public Class<FindTicketsByAuthorQuery> getQueryType() {
        return FindTicketsByAuthorQuery.class;
    }
}
