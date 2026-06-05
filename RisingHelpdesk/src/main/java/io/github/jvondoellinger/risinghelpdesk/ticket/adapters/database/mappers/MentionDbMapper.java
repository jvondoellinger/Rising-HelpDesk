package io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.mappers;

import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.entities.MentionDbEntity;
import io.github.jvondoellinger.risinghelpdesk.ticket.adapters.database.entities.TicketDbEntity;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Mention;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class MentionDbMapper {
	public MentionDbEntity from(Mention mention) {
		return new MentionDbEntity(
			   mention.getId(),
			   mention.getMentionedById(),
			   mention.getUserId(),
			   mention.getMentionedAt(),
			   blankTicket(mention.getTicketId())
		);
	}

	private TicketDbEntity blankTicket(UUID ticketId) {
		var ticket = new TicketDbEntity();
		ticket.setId(ticketId);
		return ticket;
	}
}
