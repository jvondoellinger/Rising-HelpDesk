package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.create_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.security.AuthenticatedUser;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.ticket.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketService implements CreateTicketHandler {
	private final TicketRepository repository;
	private final QueueRepository queueRepository;
	private final AuthenticatedUser authenticatedUser;

	@Override
	public ResultB<Void> handle(CreateTicket cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var queueOptional = queueRepository.findById(cmd.queueId());

				   if (queueOptional.isEmpty()){
					   return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found."));
				   }

				   var ticket = new Ticket(
						 cmd.title(),
						 queueOptional.get(),
						 authenticatedUser.getCurrentUserId(),
						 cmd.deadline()
				   );

				   repository.save(ticket);
				   return ResultB.create();
			   });
	}

	@Override
	public Class<CreateTicket> getCommandType() {
		return CreateTicket.class;
	}
}
