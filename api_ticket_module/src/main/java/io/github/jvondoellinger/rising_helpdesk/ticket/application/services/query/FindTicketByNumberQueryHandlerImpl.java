package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByNumberQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByNumberQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByNumberQueryHandlerImpl
	   implements FindTicketByNumberQueryHandler {

	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public Result<TicketDetails> handle(FindTicketByNumberQuery query) {
		var num = query.number();

		if (num == null) {
			return Result.failure("Protocol number is blank.");
		}

		var optional = repository.findByNumber(num);

		if (optional.isEmpty()) {
			return Result.failure("No ticket found.");
		}

		return Result.success(mapper.details(optional.get()));
	}

	@Override
	public Class<FindTicketByNumberQuery> getQueryType() {
		return FindTicketByNumberQuery.class;
	}
}
