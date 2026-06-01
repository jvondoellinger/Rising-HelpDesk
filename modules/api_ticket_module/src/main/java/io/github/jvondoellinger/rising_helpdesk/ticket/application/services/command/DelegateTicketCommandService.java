package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.ticket.DelegateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.DelegateTicketCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class DelegateTicketCommandService implements DelegateTicketCommandHandler {
	private final TicketRepository repository;
	private final QueueRepository queueRepository;

	@Override
	public ResultB<Void> handle(DelegateTicketCommand cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var queueOptional = queueRepository.findById(cmd.queueId());

				   if (queueOptional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found"));
				   }
				   var ticketOptional = repository.findById(cmd.ticketId());

				   if (ticketOptional.isEmpty()) {
					   return (ResultB<Void>)(ResultB<?>)ResultB.create().map(v -> new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var ticket = ticketOptional.get();
				   var queue = queueOptional.get();

				   ticket.delegate(queue);

				   repository.save(ticket);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<DelegateTicketCommand> getCommandType() {
		return DelegateTicketCommand.class;
	}
}
