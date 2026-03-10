package io.github.jvondoellinger.rising_helpdesk.ticket.adapter.in;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.commands.CreateQueueCommand;
import io.github.jvondoellinger.rising_helpdesk.ticket.application.handlers.commands.CreateQueueCommandHandler;

import io.github.jvondoellinger.rising_helpdesk.ticket.application.services.bus.CommandBus;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
	private final CreateQueueCommandHandler useCase;
	private final CommandBus bus;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateQueueCommand requestDTO) {
		return null;
	}

	// Remover posteriormente
	@PostMapping("/test")
	public ResponseEntity<?> test() {


		return ResponseEntity.accepted().body(bus.send(new CreateQueueCommand("Tets,", "Test", null)));
	}
}
