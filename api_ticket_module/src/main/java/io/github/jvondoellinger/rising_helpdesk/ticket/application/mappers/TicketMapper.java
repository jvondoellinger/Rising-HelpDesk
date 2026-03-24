package io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Pagination;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.Ticket;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Queue;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketMapper {
	private final QueueMapper queueMapper;

	/**
	 * @return Ticket com uma Queue só com o campo Id preenchido.
	 */
	public Ticket from(@NonNull CreateTicketCommand command) {
		return new Ticket(
			   command.title(),
			   blankQueue(command.queueId()),
			   command.openedBy(),
			   command.deadline()
		);
	}

	public List<TicketDetails> details(List<Ticket> tickets) {
		return tickets.stream().map(this::details).toList();
	}

	public TicketDetails details(Ticket ticket) {
		return new TicketDetails(
			   ticket.getId(),
			   ticket.getNumber(),
			   ticket.getTitle(),
			   queueMapper.details(ticket.getQueue()),
			   ticket.getMentions(),
			   ticket.getDeadline(),
			   ticket.getOpenedBy(),
			   ticket.getOpenedOn(),
			   ticket.getLastUpdatedBy(),
			   ticket.getLastUpdatedOn()
		);
	}

	public Pagination<TicketDetails> detailsPagination(Pagination<Ticket> ticketPagination) {
		var items = ticketPagination.items();
		var detailsList = details(items);
		return Pagination.of(detailsList, ticketPagination.page(), ticketPagination.totalPages());
	}

	private Queue blankQueue(UUID queuId) {
		return new Queue(queuId, null, null, null, null, null, null);
	}
}
