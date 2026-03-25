package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.command;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.bus.QueryBus;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateTicketCommandHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindQueueByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTicketCommandService implements CreateTicketCommandHandler {
	private final TicketRepository repository;
	private final QueryBus queryBus;
	private final TicketMapper mapper;

	@Override
	public Result<Void> handle(CreateTicketCommand cmd) {
		// Estou considerando caching nas queries, assim sendo mais rápido que um exists() no repositorio das filas
		var queueResult = queryBus.send(new FindQueueByIdQuery(cmd.queueId()));

		if (queueResult.isFailure())
			return new Result.Failure<>("No queue found.");

		var ticket = mapper.from(cmd);
		repository.save(ticket);

		return new Result.Success<>(null);
	}

	@Override
	public Class<CreateTicketCommand> getCommandType() {
		return CreateTicketCommand.class;
	}
}
