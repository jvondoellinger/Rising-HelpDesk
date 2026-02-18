package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.sharedkernel.application_commons.CommandResult;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.CreateTicketCommandUseCases;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.usecases.TicketQueryUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CreateTicketCommandUseCases createTicketUseCases;
	private final TicketQueryUseCases ticketQueryUseCases;

	@GetMapping
	public List<TicketDetails> get() {
		return ticketQueryUseCases.queryByPagination(0, 100).items();
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateTicketCommand requestDTO) {

		var result = createTicketUseCases.execute(requestDTO);

		if (result instanceof CommandResult.Success<TicketDetails> success)
			return ResponseEntity.ok(success.value());
		else if (result instanceof CommandResult.Failure<TicketDetails> failure)
			return ResponseEntity.badRequest().body(failure.error().getMessage());
		else
			return ResponseEntity.internalServerError().body("No result type found.");
	}
}
