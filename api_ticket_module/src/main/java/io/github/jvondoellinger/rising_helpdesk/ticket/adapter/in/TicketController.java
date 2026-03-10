package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.dtos.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateTicketCommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketController {
	private final CreateTicketCommandHandler createTicketUseCases;

	@GetMapping
	public List<TicketDetails> get() {
		return null;
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateTicketCommand requestDTO) {
		return null;
	}
}
