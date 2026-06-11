package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.add_ticket_mention;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class AddTicketMentionEndpoint {
	private final AddTicketMentionHandler handler;

	@PatchMapping
	ResponseEntity<?> addMention(@RequestBody AddTicketMentionRequest request) {
		var cmd = new AddTicketMention(request.userId(), request.ticketId());
		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}
