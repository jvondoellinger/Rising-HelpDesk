package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.close_ticket;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class CloseTicketEndpoint {
	private final CloseTicketHandler handler;

	@PatchMapping("/api/ticket/close")
	ResponseEntity<?> closeTicket(@RequestBody CloseTicketRequest request) {
		var cmd = new CloseTicket(request.ticketId());
		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}
