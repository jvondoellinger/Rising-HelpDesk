package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_id;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByIdService implements FindTicketByIdHandler {
	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	// @Cacheable(value = "ticket-by-id", key = "#query.id()")
	public ResultB<TicketDetails> handle(FindTicketById query) {
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
	public Class<FindTicketById> getQueryType() {
		return FindTicketById.class;
	}
}
