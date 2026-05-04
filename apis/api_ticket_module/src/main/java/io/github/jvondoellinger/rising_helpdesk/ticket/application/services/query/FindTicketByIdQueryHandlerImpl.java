package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.Result;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.ResultTransformerStep;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.kernel.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindTicketByIdQueryHandlerImpl implements FindTicketByIdQueryHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public ResultTransformerStep<TicketDetails> handle(FindTicketByIdQuery query) {
		return ResultTransformerStep.create()
			   .flatMap(aVoid -> {
				   var id = query.id();

				   if (id == null) {
					   return Result.error(new DomainError("ID_IS_BLANK", "ID is blank."));
				   }

				   var optional = repository.findById(id);

				   if (optional.isEmpty()) {
					   return Result.success(null);
				   }

				   var ticket = optional.get();
				   var details = mapper.details(ticket);

				   return Result.success(details);
			   });
	}

	@Override
	public Class<FindTicketByIdQuery> getQueryType() {
		return FindTicketByIdQuery.class;
	}
}
