package io.github.jvondoellinger.rising_helpdesk.ticket.application.services.query;

import io.github.jvondoellinger.rising_helpdesk.shared.application.short_circuiting.ResultB;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.queries.FindTicketByIdQueryHandler;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.mappers.TicketMapper;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.FindTicketByIdQuery;
import io.github.jvondoellinger.rising_helpdesk.ticket.domain.repository.TicketRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.github.jvondoellinger.rising_helpdesk.shared.application.result.DomainError;

@Service
@AllArgsConstructor
public class FindTicketByIdQueryHandlerImpl implements FindTicketByIdQueryHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	public ResultB<TicketDetails> handle(FindTicketByIdQuery query) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var id = query.id();

				   if (id == null) {
					   return ResultB.error(new DomainError("ID_IS_BLANK", "ID is blank."));
				   }

				   var optional = repository.findById(id);

				   if (optional.isEmpty()) {
					   return (ResultB<TicketDetails>)(ResultB<?>)ResultB.create();
				   }

				   var ticket = optional.get();
				   var details = mapper.details(ticket);

				   return ResultB.of(details);
			   });
	}

	@Override
	public Class<FindTicketByIdQuery> getQueryType() {
		return FindTicketByIdQuery.class;
	}
}
