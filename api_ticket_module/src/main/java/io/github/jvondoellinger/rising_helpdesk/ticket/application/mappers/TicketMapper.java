package io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.interaction.InteractionsHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketMapper {
	public Ticket from(CreateTicketCommand command) {
		return new Ticket(
			   command.title(),
			   new InteractionsHistory(),
			   command.queueId(),
			   command.openedBy(),
			   command.deadline()
		);
	}

	public List<TicketDetails> details(List<Ticket> tickets) {
		return tickets.stream().map(this::details).toList();
	}

	public TicketDetails details(Ticket ticket) {
		return new TicketDetails(
			   ticket.getNumber(),
			   ticket.getTitle(),
			   ticket.getQueueId(),
			   ticket.getMentions(),
			   ticket.getDeadline(),
			   ticket.getOpenedBy(),
			   ticket.getOpenedOn(),
			   ticket.getLastUpdatedBy(),
			   ticket.getLastUpdatedOn());
	}
}
