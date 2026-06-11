package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_number;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import io.github.jvondoellinger.risinghelpdesk.ticket.domain.valueObjects.TicketNumber;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class FindTicketByNumberEndpoint {
	private final FindTicketByNumberHandler handler;

	@GetMapping("/number")
	ResponseEntity<?> findTicketByNumber(@RequestParam String number) {
		var protocolNumber = TicketNumber.parse(number);

		if (Objects.isNull(protocolNumber)) {
			return ResponseEntity
				   .badRequest()
				   .body("TicketNumber in wrong format.");
		}

		var query = new FindTicketByNumber(protocolNumber);
		var result = handler.handle(query);

		return HttpResultMapper.from(result);
	}
}
