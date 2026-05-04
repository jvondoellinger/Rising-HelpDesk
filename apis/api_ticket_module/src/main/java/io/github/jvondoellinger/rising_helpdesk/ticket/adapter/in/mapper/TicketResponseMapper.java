package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.MentionsResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.PageResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.TicketResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketResponseMapper {
    private final QueueResponseMapper queueResponseMapper;

    public TicketResponse from(TicketDetails request) {
        var userIds = request
                .mentions()
                .stream()
                .map(Mention::getId)
                .toList();
        var mentions = new MentionsResponse(userIds);

        return new TicketResponse(
                request.ticketNumber().toString(),
                request.title(),
                queueResponseMapper.from(request.queue()),
                mentions,
                request.deadline(),
                request.openedBy(),
                request.openedOn(),
                request.lastUpdatedBy(),
                request.lastUpdatedOn()
        );
    }

    public PageResponse<TicketResponse> from(Pagination<TicketDetails> detailsPagination) {
        var responseItems = detailsPagination
                .items()
                .stream()
                .map(this::from)
                .toList();

        return new PageResponse<>(responseItems, detailsPagination.page(), detailsPagination.size(), detailsPagination.totalPages());
    }

}
