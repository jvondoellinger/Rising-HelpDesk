package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_author;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class FindTicketByAuthorEndpoint {
	private final FindTicketsByAuthorHandler handler;

	@GetMapping("/author/{authorId}")
	ResponseEntity<?> findTicketByAuthor(
		   @PathVariable("authorId") UUID authorId,
		   @RequestParam(required = false) int page,
		   @RequestParam(required = false) int size) {
		var query = new FindTicketsByAuthor(authorId, page, size);
		var result = handler.handle(query);

		return HttpResultMapper.from(result);
	}
}
