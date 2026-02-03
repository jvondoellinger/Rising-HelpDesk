package com.github.jvondoellinger.agp_protocol.ticket_module.application.mappers;

import com.github.jvondoellinger.agp_protocol.application.ticket.valueObjects.MentionsDTO;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.commands.CreateTicketCommand;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.queries.TicketDetails;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.Ticket;
import com.github.jvondoellinger.agp_protocol.ticket_module.domain.interaction.InteractionsHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketMapper {
	public Ticket from(CreateTicketCommand command) {
		return new Ticket(
			   command.title(),
			   new InteractionsHistory(),
			   command.queueId(),
			   command.mentions(),
			   command.openedBy(),
			   command.deadline()
		);
	}

	public List<TicketDetails> details(List<Ticket> tickets) {
		return tickets.stream().map(this::details).toList();
	}

	public TicketDetails details(Ticket ticket) {
		return new TicketDetails(
			   ticket.number(),
			   ticket.title(),
			   ticket.queueId(),
			   ticket.mentions(),
			   ticket.deadline(),
			   ticket.openedBy(),
			   ticket.openedOn(),
			   ticket.lastUpdatedBy(),
			   ticket.lastUpdatedOn());
	}
}
