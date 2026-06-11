package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_id;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class FindTicketByIdEndpoint {
	private final FindTicketByIdHandler handler;

	@GetMapping("/{id}")
	ResponseEntity<?> findTicketById(@PathVariable("id")UUID id) {
		var query = new FindTicketById(id);
		var result = handler.handle(query);

		return HttpResultMapper.from(result);
	}
}
