package com.github.jvondoellinger.agp_protocol.ticket_module.adapter.in;

import com.github.jvondoellinger.agp_protocol.ticket_module.application.commands.CreateQueueCommand;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.queries.QueueDetails;
import com.github.jvondoellinger.agp_protocol.ticket_module.application.usecases.CreateQueueCommandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queue")
@AllArgsConstructor
public class QueueController {
	private final CreateQueueCommandUseCase useCase;

	@PostMapping
	public QueueDetails create(@RequestBody CreateQueueCommand requestDTO) {
		return useCase.execute(requestDTO);
	}
}
