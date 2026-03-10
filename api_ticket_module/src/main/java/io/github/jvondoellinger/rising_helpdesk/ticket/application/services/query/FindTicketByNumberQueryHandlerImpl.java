package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.KernelException;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.Result;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByNumberQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByNumberQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.TicketRepository;
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
			return new Result.Failure<>(new KernelException("Protocol number is blank."));
		}

		var result = repository.findByNumber(num).orElse(null);

		var details = mapper.details(result);
		return new Result.Success<>(details);
	}
}
