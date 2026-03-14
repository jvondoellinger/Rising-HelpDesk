package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.MentionsResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.responses.TicketResponse;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import org.springframework.stereotype.Service;

@Service
public class TicketResponseMapper {
    public TicketResponse from(TicketDetails request) {
        var userIds = request
                .mentions()
                .readonlyList()
                .stream()
                .map(UserProfileId::toString)
                .toList();
        var mentions = new MentionsResponse(userIds);

        return new TicketResponse(
                request.ticketNumber().toString(),
                request.title(),
                request.queueId().toString(),
                mentions,
                request.deadline(),
                request.openedBy().toString(),
                request.openedOn(),
                request.lastUpdatedBy().toString(),
                request.lastUpdatedOn()
        );
    }

    public Pagination<TicketResponse> from(Pagination<TicketDetails> detailsPagination) {
        var responseItems = detailsPagination
                .items()
                .stream()
                .map(this::from)
                .toList();

        return new Pagination<>(responseItems, detailsPagination.page(), detailsPagination.size(), detailsPagination.totalPages());
    }

}
