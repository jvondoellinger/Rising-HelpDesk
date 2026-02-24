package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.FindTicketByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByIdQueryHandlerImpl implements FindTicketByIdQueryHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public Result<TicketDetails> handle(FindTicketByIdQuery query) {
		var id = query.id();

		if (id == null) {
			return new Result.Failure<>(new KernelException("ID is blank."));
		}

		var result = repository.queryById(id);
		var details = mapper.details(result);

		return new Result.Success<>(details);
	}
}
