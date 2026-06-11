package io.github.jvondoellinger.risinghelpdesk.ticket.features.ticket.find_ticket_by_pagination;

import io.github.jvondoellinger.risinghelpdesk.shared.mapper.HttpResultMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class FindTicketByPaginationEndpoint {
	private final FindTicketsByPaginationHandler handler;

	@GetMapping
	ResponseEntity<?> findTicketByPagination(
		   	@RequestParam int page,
			@RequestParam int size

	) {
		var query = new FindTicketsByPagination(page, size);
		var result = handler.handle(query);

		return HttpResultMapper.from(result);
	}
}
