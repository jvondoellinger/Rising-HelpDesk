package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.change_ticket_queue;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChangeTicketQueueService implements ChangeTicketQueueHandler {
	private final TicketRepository repository;
	private final QueueRepository queueRepository;

	@Override
	public ResultB<Void> handle(ChangeTicketQueue cmd) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var queueOptional = queueRepository.findById(cmd.queueId());
				   if (queueOptional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_QUEUE_FOUND", "No queue found"));
				   }

				   var ticketOptional = repository.findById(cmd.ticketId());
				   if (ticketOptional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }

				   var ticket = ticketOptional.get();
				   var queue = queueOptional.get();

				   ticket.delegate(queue);

				   repository.save(ticket);

				   return ResultB.create();
			   });
	}

	@Override
	public Class<ChangeTicketQueue> getCommandType() {
		return ChangeTicketQueue.class;
	}
}
