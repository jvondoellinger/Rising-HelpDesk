package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.close_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.cqrs.CommandHandler;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CloseTicketService implements CloseTicketHandler {
	private final TicketRepository repository;

	@Override
	public ResultB<Void> handle(CloseTicket cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var optionalTk = repository.findById(cmd.ticketId());
				   if (optionalTk.isEmpty()) {
					   return ResultB.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var tk = optionalTk.get();
				   tk.close();
				   repository.save(tk);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<CloseTicket> getCommandType() {
		return CloseTicket.class;
	}
}
