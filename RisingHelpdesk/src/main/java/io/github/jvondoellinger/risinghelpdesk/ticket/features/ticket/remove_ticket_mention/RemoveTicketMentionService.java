package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.remove_ticket_mention;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.MentionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Mention;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RemoveTicketMentionService implements RemoveTicketMentionHandler {
	private final TicketRepository ticketRepository;
	private final MentionRepository mentionRepository;
	private final AuthenticatedUser authenticatedUser;

	@Override
	public ResultB<Void> handle(RemoveTicketMention cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   if (ticketRepository.existsById(cmd.ticketId())) {
					   return ResultB.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var mention = new Mention(
						 cmd.userId(),
						 authenticatedUser.getCurrentUserId(),
						 cmd.ticketId()
				   );

				   mentionRepository.save(mention);
				   return ResultB.create();
			   });
	}

	@Override
	public Class<RemoveTicketMention> getCommandType() {
		return RemoveTicketMention.class;
	}
}
