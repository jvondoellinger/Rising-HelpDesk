package io.github.jvondoellinger.rising_helpdesk.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.application.commands.CreateTicketCommand;
import io.github.jvondoellinger.rising_helpdesk.application.queries.TicketDetails;
import io.github.jvondoellinger.rising_helpdesk.application.usecases.CreateTicketCommandUseCases;
import io.github.jvondoellinger.rising_helpdesk.application.usecases.TicketQueryUseCases;
import lombok.AllArgsConstructor;
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
	public TicketDetails create(@RequestBody CreateTicketCommand requestDTO) {
		return createTicketUseCases.execute(requestDTO);
	}
}
