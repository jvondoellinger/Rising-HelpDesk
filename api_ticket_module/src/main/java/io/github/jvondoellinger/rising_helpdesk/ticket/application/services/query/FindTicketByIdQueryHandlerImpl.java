package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByIdQueryHandlerImpl implements FindTicketByIdQueryHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public Result<TicketDetails, String> handle(FindTicketByIdQuery query) {
		var id = query.id();

		if (id == null) {
			return Result.failure("ID is blank.");
		}

		var optional = repository.findById(id);

		if (optional.isEmpty()) {
			return Result.success();
		}

		var ticket = optional.get();
		var details = mapper.details(ticket);

		return Result.success(details);
	}

	@Override
	public Class<FindTicketByIdQuery> getQueryType() {
		return FindTicketByIdQuery.class;
	}
}
