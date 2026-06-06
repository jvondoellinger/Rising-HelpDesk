package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.create_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class CreateTicketEndpoint {
	private final CreateTicketHandler handler;

	@PostMapping
	ResponseEntity<?> createTicket(@RequestBody CreateTicketRequest request) {
		var cmd = new CreateTicket(request.title(), request.queueId(), request.deadline());
		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}
