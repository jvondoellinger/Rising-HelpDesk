package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.UserProfileId;
import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.CreateTicketRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.QueueId;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mentions;
import org.springframework.stereotype.Service;

@Service
public class TicketCommandMapper {
    public CreateTicketCommand from(CreateTicketRequest request) {
        var queueId = QueueId.of(request.queueId());
        var openedBy = UserProfileId.of(request.openedBy());
        var usersMentioned = request
                .mentions()
                .userIds()
                .stream()
                .map(UserProfileId::of)
                .toList();
        var mentions = new Mentions(usersMentioned);

        return new CreateTicketCommand(
                request.title(),
                queueId,
                mentions,
                request.deadline(),
                openedBy
        );
    }

}
