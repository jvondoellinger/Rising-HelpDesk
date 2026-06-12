package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_author;

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
public class FindTicketByAuthorService implements FindTicketsByAuthorHandler {
    private final TicketRepository repository;
    private final TicketMapper mapper;

    @Override
    @Cacheable(value = "ticket-by-area", key = "#query.author() + ':' + #query.page() + ':' + #query.size()")
    public ResultB<Pagination<TicketDetails>> handle(FindTicketsByAuthor query) {
        return ResultB.create()
                .flatMap(aVoid -> {
                    var tks = repository.findByAuthorId(query.author(), PaginationFilter.of(query.page(), query.size()));

                    var details = tks.items()
                            .stream()
                            .map(mapper::details)
                            .toList();

                    var pagination = Pagination.of(details, tks.page(), tks.totalPages());

                    return ResultB.of(pagination);
                });
    }

    @Override
    public Class<FindTicketsByAuthor> getQueryType() {
        return FindTicketsByAuthor.class;
    }
}
