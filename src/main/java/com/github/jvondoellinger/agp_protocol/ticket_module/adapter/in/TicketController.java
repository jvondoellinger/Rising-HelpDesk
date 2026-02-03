package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.in;

import com.github.jvondoellinger.agp_protocol.ticket_module.application.dtos.CreateTicketCommand;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.queries.TicketDetails;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.usecases.CreateTicketCommandUseCases;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.usecases.TicketQueryUseCases;
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
