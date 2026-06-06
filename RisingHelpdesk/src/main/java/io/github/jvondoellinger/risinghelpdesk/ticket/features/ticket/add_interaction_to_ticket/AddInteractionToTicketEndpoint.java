package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_interaction_to_ticket;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class AddInteractionToTicketEndpoint {
	private final AddInteractionToTicketHandler handler;

	@PatchMapping
	ResponseEntity<?> addInteractionOnTicket(@RequestBody AddInteractionToTicketRequest request) {
		var cmd = new AddInteractionToTicket(request.text(), request.ticketId());
		var result = handler.handle(cmd);

		return ResponseEntity.ok().build();
	}
}
