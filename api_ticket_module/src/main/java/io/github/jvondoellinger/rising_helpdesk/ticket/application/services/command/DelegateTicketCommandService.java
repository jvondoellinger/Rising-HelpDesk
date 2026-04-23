package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.DelegateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.DelegateTicketCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.QueueRepository;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DelegateTicketCommandService implements DelegateTicketCommandHandler {
	private final TicketRepository repository;
	private final QueueRepository queueRepository;

	@Override
	public Result<Void, String> handle(DelegateTicketCommand cmd) {
		var queueOptional = queueRepository.findById(cmd.queueId());

		if (queueOptional.isEmpty()) {
			return Result.failure("No queue found");
		}
		var ticketOptional = repository.findById(cmd.ticketId());

		if (ticketOptional.isEmpty()) {
			return Result.failure("No ticket found.");
		}

		var ticket = ticketOptional.get();
		var queue = queueOptional.get();

		ticket.delegate(queue);

		repository.save(ticket);

		return Result.success(null);
	}

	@Override
	public Class<DelegateTicketCommand> getCommandType() {
		return DelegateTicketCommand.class;
	}
}
