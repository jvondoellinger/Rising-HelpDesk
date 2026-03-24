package io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.AddTicketMentionCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.aggregate.ticket.entities.Mention;
import org.springframework.stereotype.Service;

@Service
public final class MentionMapper {
	public Mention from(AddTicketMentionCommand cmd) {
		return new Mention(cmd.userId(), cmd.authorId(), cmd.ticketId());
	}
}
