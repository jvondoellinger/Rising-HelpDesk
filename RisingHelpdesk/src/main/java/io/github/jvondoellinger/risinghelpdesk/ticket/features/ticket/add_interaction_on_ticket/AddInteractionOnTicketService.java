package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_on_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.InteractionRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.entities.Interaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddInteractionOnTicketService implements AddInteractionOnTicketHandler {
	private final InteractionRepository repository;
	private final TicketRepository ticketRepository;
	private final AuthenticatedUser authenticatedUser;

	@Override
	public ResultB<Void> handle(AddInteractionOnTicket cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   if ( !ticketRepository.existsById(cmd.ticketId()) )
					   return ResultB.error(new DomainError("NO_TICKETS_WERE_FOUND_TO_ADD_INTERACTION", "No tickets were found to add interaction.."));

				   var interaction = new Interaction(
						 cmd.text(),
						 authenticatedUser.getCurrentUserId(),
						 cmd.ticketId()
				   );

				   repository.save(interaction);
				   return ResultB.create();
			   });

	}

	@Override
	public Class<AddInteractionOnTicket> getCommandType() {
		return AddInteractionOnTicket.class;
	}
}
