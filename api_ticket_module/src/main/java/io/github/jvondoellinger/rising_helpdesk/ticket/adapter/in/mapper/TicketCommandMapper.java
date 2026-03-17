package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.mapper;

import io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in.requests.ticket.CreateTicketRequest;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import org.springframework.stereotype.Service;

@Service
public class TicketCommandMapper {
    public CreateTicketCommand from(CreateTicketRequest request) {
        return new CreateTicketCommand(
                request.title(),
                request.queueId(),
                request.deadline(),
                request.openedBy()
        );
    }

}
