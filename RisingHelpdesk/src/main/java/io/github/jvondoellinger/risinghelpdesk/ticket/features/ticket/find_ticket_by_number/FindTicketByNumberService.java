package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_number;

import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.DomainError;
import io.github.jvondoellinger.risinghelpdesk.shared.short_circuiting.ResultB;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketDetails;
import io.github.jvondoellinger.risinghelpdesk.ticket.application.TicketMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindTicketByNumberService
	   implements FindTicketByNumberHandler {

	private final TicketRepository repository;
	private final TicketMapper mapper;

	@Override
	// @Cacheable(value = "queue-by-number", key = "#query.number()")
	public ResultB<TicketDetails> handle(FindTicketByNumber query) {
		return ResultB.create()
			   .flatMap(aVoid -> {
				   var num = query.number();

				   if (num == null) {
					   return ResultB.error(new DomainError("PROTOCOL_NUMBER_IS_BLANK", "Protocol number is blank."));
				   }

				   var optional = repository.findByNumber(num);

				   if (optional.isEmpty()) {
					   return ResultB.error(new DomainError("NO_TICKET_FOUND", "No ticket found."));
				   }
				   var details = mapper.details(optional.get());
				   return ResultB.create().map(v -> details);
			   });
	}

	@Override
	public Class<FindTicketByNumber> getQueryType() {
		return FindTicketByNumber.class;
	}
}
