package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByNumberQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByNumberQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindTicketByNumberQueryHandlerImpl
	   implements FindTicketByNumberQueryHandler {

	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public ResultTransformerStep<TicketDetails> handle(FindTicketByNumberQuery query) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var num = query.number();

				   if (num == null) {
					   return Result.error(new DomainError("PROTOCOL_NUMBER_IS_BLANK", "Protocol number is blank."));
				   }

				   var optional = repository.findByNumber(num);

				   if (optional.isEmpty()) {
					   return Result.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }
				   var details = mapper.details(optional.get());
				   return Result.success(details);
			   });
	}

	@Override
	public Class<FindTicketByNumberQuery> getQueryType() {
		return FindTicketByNumberQuery.class;
	}
}
