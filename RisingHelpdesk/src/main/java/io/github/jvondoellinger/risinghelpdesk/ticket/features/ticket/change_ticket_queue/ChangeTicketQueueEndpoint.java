package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.change_ticket_queue;

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
public class ChangeTicketQueueEndpoint {
	private final ChangeTicketQueueHandler handler;

	@PatchMapping("/change/queue")
	ResponseEntity<?> changeTicketQueue(@RequestBody ChangeTicketQueueRequest request) {
		var cmd = new ChangeTicketQueue(
			   request.ticketId(),
			   request.queueId()
		);

		var result = handler.handle(cmd);

		return HttpResultMapper.from(result);
	}
}
